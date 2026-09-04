package com.manishjoshii.appcatalyst.dto.chat;

import com.manishjoshii.appcatalyst.entity.ChatEvent;
import com.manishjoshii.appcatalyst.entity.ChatSession;
import com.manishjoshii.appcatalyst.enums.MessageRole;

import java.time.Instant;
import java.util.List;

public record ChatResponse(
        Long id,
        ChatSession chatSession,
        MessageRole role,
        List<ChatEvent> events,
        String content,
        Integer tokensUsed,
        Instant createdAt
) {
}
