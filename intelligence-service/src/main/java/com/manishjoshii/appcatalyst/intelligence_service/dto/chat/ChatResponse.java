package com.manishjoshii.appcatalyst.intelligence_service.dto.chat;


import com.manishjoshii.appcatalyst.common_lib.enums.MessageRole;

import java.time.Instant;
import java.util.List;

public record ChatResponse(
        Long id,
        MessageRole role,
        List<ChatEventResponse> events,
        String content,
        Integer tokensUsed,
        Instant createdAt

) {
}