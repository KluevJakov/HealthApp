package com.healthapp.healthapp.repository;

import com.healthapp.healthapp.entity.Chat;
import com.healthapp.healthapp.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "select distinct * from chats inner join chats_members on chats.id = chats_members.chat_id where chats_members.members_id = ?1", nativeQuery = true)
    List<Chat> findByMember(Long id);
}