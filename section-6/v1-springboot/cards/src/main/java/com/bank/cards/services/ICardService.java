package com.bank.cards.services;

import com.bank.cards.dtos.CardDTO;

public interface ICardService {
    void createCard(String mobileNumber);
    CardDTO getCard(String mobileNumber);
    void updateCard(CardDTO updatedCard);

    void deleteCard(String mobileNumber);
}
