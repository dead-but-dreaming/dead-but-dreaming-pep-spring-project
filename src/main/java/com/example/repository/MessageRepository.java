package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("FROM Message WHERE id = :idVar")
    Optional<Message> findById(@Param("idVar") String id);

}
