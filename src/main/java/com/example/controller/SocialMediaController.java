package com.example.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

// import com.azul.crs.client.Response;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private final AccountService accountService;
    // private MessageService messageService;
    // private final AccountRepository accountRepository;

    @Autowired
    SocialMediaController(AccountService accountService){
        // this.accountRepository = new AccountRepository();
        this.accountService = accountService;
    }
    // @Autowired
    // SocialMediaController(){
        
    // }

    @PostMapping("/register")
    public ResponseEntity postRegister(@RequestBody Account newAccount){
        
        if (newAccount.getUsername() == ""){
            // blank username
            // System.out.println("blank username");
            return ResponseEntity.status(400).body(null);
        } else if (newAccount.getPassword().length() < 4){
            // password too short
            // System.out.println("password too short");
            return ResponseEntity.status(400).body(null);
        } else {
            // System.out.println("attempting to register new user");
            // System.out.println("newAccount: " + newAccount.getUsername());

            Account freshAccount = accountService.createNewAccount(newAccount.getUsername(), newAccount.getPassword());
            System.out.println("freshAccount after creation: " + freshAccount);
            if (freshAccount != null){
                return ResponseEntity.status(200).body(null);
            } else {
                return ResponseEntity.status(409).body(null);
            }
        }

    }

    @PostMapping("/login")
    public Account postLogin(){
        return new Account();
    }

    @PostMapping("/messages")
    public Message postMessages(){
        return new Message();
    }

    @GetMapping("/messages")
    public Message getMessages(){
        return new Message();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessageByID(@RequestParam int id){
        return new Message();
    }

    @DeleteMapping("/messages/{messageId}")
    public Message map1(@RequestParam int id){
        return new Message();
    }

    @PatchMapping("/messages/{messageId}")
    public Message map2(@RequestParam int id){
        return new Message();
    }

    @GetMapping("/accounts/{accountId}/messages")
    public Message map3(@RequestParam int id){
        return new Message();
    }

}
