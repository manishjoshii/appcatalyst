package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.chat.ChatResponse;
import com.manishjoshii.appcatalyst.entity.ChatMessage;
import com.manishjoshii.appcatalyst.entity.ChatSession;
import com.manishjoshii.appcatalyst.entity.ChatSessionId;
import com.manishjoshii.appcatalyst.mapper.ChatMapper;
import com.manishjoshii.appcatalyst.repository.ChatMessageRepository;
import com.manishjoshii.appcatalyst.repository.ChatSessionRepository;
import com.manishjoshii.appcatalyst.security.AuthUtil;
import com.manishjoshii.appcatalyst.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final AuthUtil authUtil;
    private final ChatMapper chatMapper;

    @Override
    public List<ChatResponse> getProjectChatHistory(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        ChatSession chatSession = chatSessionRepository.getReferenceById(
                new ChatSessionId(projectId, userId)
        );

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatSession(chatSession);

        return chatMapper.fromListOfChatMessage(chatMessageList);
    }
}
