package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }

    public Account getAccountById(long id){
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }else{
            return null;
        }
    }

    public boolean checkUsernameExists(String username){
        // System.out.println("Check username exists");
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        
        if(optionalAccount.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public Account createNewAccount(String username, String password){
        // System.out.println("Checking username exists");
        boolean exists = checkUsernameExists(username);
        
        if (exists == false){
            // System.out.println("Username doesn't exist.");

            Account newAccount = new Account(username, password);
            // System.out.println("Preparing to save account");
            newAccount = accountRepository.save(newAccount);
            return newAccount;
        } else {
            return null;
        }
    }

    public Account authenticateUser(String username, String password){
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(username, password);

        if (optionalAccount.isPresent()){
            System.out.println(optionalAccount.get());

            Account account = optionalAccount.get();

            if (password.equals(account.getPassword())){
                return account;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
