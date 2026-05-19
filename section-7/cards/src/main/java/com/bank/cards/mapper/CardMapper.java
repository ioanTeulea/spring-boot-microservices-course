package com.bank.cards.mapper;

import com.bank.cards.dtos.CardDTO;
import com.bank.cards.entities.Card;

public class CardMapper {
    public static CardDTO mapToCardDto(Card card) {
        return new CardDTO(
                card.getMobileNumber(),
                card.getCardNumber(),
                card.getCardType(),
                card.getTotalLimit(),
                card.getAmountUsed(),
                card.getAvailableAmount()
        );
    }
    public static Card mapToCard(CardDTO cardDTO) {
        return Card.builder()
                .mobileNumber(cardDTO.mobileNumber())
                .cardNumber(cardDTO.cardNumber())
                .cardType(cardDTO.cardType())
                .totalLimit(cardDTO.totalLimit())
                .amountUsed(cardDTO.amountUsed())
                .availableAmount(cardDTO.availableAmount())
                .build();
    }
}
