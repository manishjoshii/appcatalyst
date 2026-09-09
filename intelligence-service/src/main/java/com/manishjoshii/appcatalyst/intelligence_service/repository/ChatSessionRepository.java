package com.manishjoshii.appcatalyst.intelligence_service.repository;

import com.manishjoshii.appcatalyst.intelligence_service.entity.ChatSession;
import com.manishjoshii.appcatalyst.intelligence_service.entity.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, ChatSessionId> {
}