package com.healthapp.healthapp.repository;

import com.healthapp.healthapp.entity.Chat;
import com.healthapp.healthapp.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}