package com.bank.accounts.repositories;

import com.bank.accounts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByMobileNumber(String mobileNumber);
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
