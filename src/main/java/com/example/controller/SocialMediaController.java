package com.example.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

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

            newAccount = accountService.createNewAccount(newAccount.getUsername(), newAccount.getPassword());
            
            if (newAccount != null){
                return ResponseEntity.status(200).body(newAccount);
            } else {
                return ResponseEntity.status(409).body(null);
            }
        }

    }

    @PostMapping("/login")
    public ResponseEntity postLogin(@RequestBody Account account){

        account = accountService.authenticateUser(account.getUsername(), account.getPassword());

        if (account != null) {
            return ResponseEntity.status(200).body(account);
        } else {
            return ResponseEntity.status(401).body(null);
        }

    }

    @PostMapping("/messages")
    public ResponseEntity postMessage(@RequestBody Message newMessage){
        System.out.println(accountService.checkAccountIdExists(newMessage.getPostedBy()));
        if (newMessage.getMessageText() == ""){
            return ResponseEntity.status(400).body("");
        } else if (newMessage.getMessageText().length() > 255){
            return ResponseEntity.status(400).body("");
        } else if (accountService.checkAccountIdExists(newMessage.getPostedBy()) == null) {
            return ResponseEntity.status(400).body("");
        } else {
            // System.out.println("message: " + newMessage);
            newMessage = messageService.persistMessage(newMessage);
            
            return ResponseEntity.status(200).body(newMessage);
        }

    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageByID(@PathVariable int messageId){
        Message message = messageService.getMessageById(messageId);

        return ResponseEntity.status(200).body(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessageByID(@PathVariable int messageId){
        int numDeleted = messageService.deleteMessageByID(messageId);
        String result = "";
        
        if (numDeleted > 0){
            result += numDeleted;
        }

        return ResponseEntity.status(200).body(result);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessageByID(@RequestBody Message newMessage, @PathVariable int messageId){
        // System.out.println("in controller");
        newMessage.setMessageId(messageId);

        if (newMessage.getMessageText() == ""){
            return ResponseEntity.status(400).body("");
        } else if (newMessage.getMessageText().length() > 255){
            return ResponseEntity.status(400).body("");
        } else if (messageService.getMessageById(messageId) == null) {
            return ResponseEntity.status(400).body("");
        } else {
            // System.out.println("message: " + newMessage);
            int result = messageService.updateMessage(newMessage.getMessageText(), messageId);
            
            return ResponseEntity.status(200).body(result);
        }

    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity getAllMessagesForUser(@PathVariable int accountId){
        System.out.println("");
        List<Message> messages = messageService.getMessagesByUserID(accountId);
        return ResponseEntity.status(200).body(messages);
    }

}
