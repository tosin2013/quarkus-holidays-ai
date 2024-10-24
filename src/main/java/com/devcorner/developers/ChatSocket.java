package com.devcorner.developers;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;

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
    public String onMessage(String userMessage) {
        return assistant.chat(userMessage);
    }
}