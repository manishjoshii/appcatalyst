package com.manishjoshii.appcatalyst.intelligence_service.service;


import com.manishjoshii.appcatalyst.intelligence_service.dto.chat.ChatResponse;

import java.util.List;

public interface ChatService {

    List<ChatResponse> getProjectChatHistory(Long projectId);
}