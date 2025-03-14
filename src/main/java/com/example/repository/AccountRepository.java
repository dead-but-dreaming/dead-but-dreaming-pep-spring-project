package com.example.repository;

import com.example.entity.Account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("FROM Account WHERE username = :usernameVar")
    Optional<Account> findByUsername(@Param("usernameVar") String username);

    @Query("FROM Account WHERE username = :usernameVar AND password = :passwordVar")
    Optional<Account> findByUsernameAndPassword(@Param("usernameVar") String username, @Param("passwordVar") String password);

    @Query("FROM Account WHERE accountId = :idVar")
    Optional<Account> findByAccountId(@Param("idVar") int accountId);

}
