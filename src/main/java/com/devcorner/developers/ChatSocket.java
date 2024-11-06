package com.devcorner.developers;

import io.quarkus.logging.Log;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;

import io.quarkiverse.langchain4j.runtime.aiservice.GuardrailException;

@WebSocket(path = "/chat")
public class ChatSocket {

    private final AssistantForCostumeSupport assistant;

    public ChatSocket(AssistantForCostumeSupport assistant) {
        this.assistant = assistant;
    }

    @OnOpen
    public String onOpen() {
        return "Hello from your Halloween Costume Creator, how can we help you?";
    }

    @OnTextMessage
    public String onMessage(String message) {
        try {
            return assistant.chat(message);
        } catch (GuardrailException e) {
            Log.errorf(e, "Error calling the LLM: %s", e.getMessage());
            return "Sorry, I am unable to process your request at the moment. It's not something I'm allowed to do.";
        }
    }
}