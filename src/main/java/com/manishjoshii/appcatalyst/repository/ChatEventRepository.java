package com.manishjoshii.appcatalyst.repository;

import com.manishjoshii.appcatalyst.entity.ChatEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatEventRepository extends JpaRepository<ChatEvent, Long> {
}
