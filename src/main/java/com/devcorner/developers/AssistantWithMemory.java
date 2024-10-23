package com.devcorner.developers;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface AssistantWithMemory {
    String chat(@MemoryId Integer memoryId, @UserMessage String message);
}
