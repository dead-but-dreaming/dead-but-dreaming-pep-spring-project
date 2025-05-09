package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("FROM Message WHERE messageId = :idVar")
    Optional<Message> findBymessageId(@Param("idVar") int messageId);

    @Query("FROM Message")
    List<Message> getAllMessages();

    @Query("FROM Message WHERE postedBy = :idVar")
    List<Message> getMessagesByUserID(@Param("idVar") int postedBy);

    @Modifying
    @Query("DELETE FROM Message WHERE messageId = :idVar")
    int deleteMessageByID(@Param("idVar") int messageId);

    @Modifying
    @Query("UPDATE FROM Message SET messageText = :textVar WHERE messageId = :idVar")
    int updateMessage(@Param("textVar") String messageText, @Param("idVar") int messageId);

}
