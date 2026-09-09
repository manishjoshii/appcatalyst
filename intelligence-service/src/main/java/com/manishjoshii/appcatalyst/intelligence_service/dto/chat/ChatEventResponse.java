package com.manishjoshii.appcatalyst.intelligence_service.dto.chat;


import com.manishjoshii.appcatalyst.common_lib.enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata
) {
}