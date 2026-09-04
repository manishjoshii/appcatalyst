package com.manishjoshii.appcatalyst.mapper;

import com.manishjoshii.appcatalyst.dto.chat.ChatResponse;
import com.manishjoshii.appcatalyst.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    List<ChatResponse> fromListOfChatMessage(List<ChatMessage> chatMessageList);
}
