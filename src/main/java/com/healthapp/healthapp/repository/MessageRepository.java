package com.healthapp.healthapp.repository;

import com.healthapp.healthapp.entity.Chat;
import com.healthapp.healthapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}