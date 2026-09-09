package com.manishjoshii.appcatalyst.intelligence_service.repository;

import com.manishjoshii.appcatalyst.intelligence_service.entity.ChatEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEventRepository extends JpaRepository<ChatEvent, Long> {
}