package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.chat.ChatResponse;

import java.util.List;

public interface ChatService {

    List<ChatResponse> getProjectChatHistory(Long projectId);
}
