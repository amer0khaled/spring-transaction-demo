package com.amer.transactionsdemo.repositories;

import com.amer.transactionsdemo.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
