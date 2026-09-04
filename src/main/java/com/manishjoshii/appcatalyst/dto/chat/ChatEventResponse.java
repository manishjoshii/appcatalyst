package com.manishjoshii.appcatalyst.dto.chat;

import com.manishjoshii.appcatalyst.enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata
) {
}
