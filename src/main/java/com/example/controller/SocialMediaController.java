package com.example.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    
    @PostMapping("/register")
    public Account postRegister(){
        return new Account();
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
