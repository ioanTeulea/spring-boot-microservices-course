package com.bank.cards.repositories;

import com.bank.cards.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByMobileNumber(String cardNumber);
    Optional<Card> findByCardNumber(String cardNumber);
}
