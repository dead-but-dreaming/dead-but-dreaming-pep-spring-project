package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("FROM Message WHERE messageId = :idVar")
    Optional<Message> findBymessageId(@Param("idVar") int messageId);

}
