package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.chat.StreamResponse;
import reactor.core.publisher.Flux;

public interface AiGenerationService {
    Flux<StreamResponse> streamResponse(String message, Long projectId);
}
