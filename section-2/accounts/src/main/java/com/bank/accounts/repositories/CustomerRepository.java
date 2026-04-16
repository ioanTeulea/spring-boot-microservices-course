package com.bank.accounts.repositories;

import com.bank.accounts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByMobileNumber(String mobileNumber);
}
