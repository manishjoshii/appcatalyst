package com.manishjoshii.appcatalyst.repository;

import com.manishjoshii.appcatalyst.entity.ChatSession;
import com.manishjoshii.appcatalyst.entity.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, ChatSessionId> {
}
