package com.bank.cards.services;

import com.bank.cards.constants.CardsConstants;
import com.bank.cards.dtos.CardDTO;
import com.bank.cards.entities.Card;
import com.bank.cards.exceptions.CardAlreadyExistsException;
import com.bank.cards.exceptions.ResourceNotFoundException;
import com.bank.cards.mapper.CardMapper;
import com.bank.cards.repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardService implements ICardService {

    private final CardRepository cardRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }

        cardRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardDTO getCard(String mobileNumber) {
     Card exsitingCard= cardRepository.findByMobileNumber(mobileNumber)
             .orElseThrow(()->new ResourceNotFoundException(Card.class.getName(),"mobileNumber",mobileNumber));
     return CardMapper.mapToCardDto(exsitingCard);

    }

    @Override
    public void updateCard(CardDTO updatedCard) {

        Card existingCard = cardRepository.findByCardNumber(updatedCard.cardNumber())
                .orElseThrow(() -> new ResourceNotFoundException(
                        Card.class.getName(),
                        "cardNumber",
                        updatedCard.cardNumber()
                ));

        if (updatedCard.cardType() != null) {
            existingCard.setCardType(updatedCard.cardType());
        }

        if (updatedCard.totalLimit() != null) {
            existingCard.setTotalLimit(updatedCard.totalLimit());
        }

        if (updatedCard.amountUsed() != null) {
            existingCard.setAmountUsed(updatedCard.amountUsed());
        }

        if (updatedCard.availableAmount() != null) {
            existingCard.setAvailableAmount(updatedCard.availableAmount());
        }

        cardRepository.save(existingCard);
    }

    @Override
    public void deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
    }

    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        newCard.setCardNumber(generateCardNumber());
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
    public String generateCardNumber() {
        Random random = new Random();

        StringBuilder card = new StringBuilder("4");

        for (int i = 0; i < 14; i++) {
            card.append(random.nextInt(10));
        }

        int sum = 0;
        boolean alternate = true;

        for (int i = card.length() - 1; i >= 0; i--) {
            int n = card.charAt(i) - '0';

            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }

            sum += n;
            alternate = !alternate;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        card.append(checkDigit);

        return card.toString();
    }


}
