package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message persistMessage(Message message){
        return messageRepository.save(message);
    }

    public Message getMessageById(int id){
        Optional<Message> optionalMessage = messageRepository.findBymessageId(id);

        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    public int updateMessage(String text, int id){
        return messageRepository.updateMessage(text, id);
    }

    public List<Message> getMessagesByUserID(int id){
        return messageRepository.getMessagesByUserID(id);
    }

    public List<Message> getAllMessages(){
        return messageRepository.getAllMessages();
    }

    public int deleteMessageByID(int id){
        return messageRepository.deleteMessageByID(id);
    }
}
