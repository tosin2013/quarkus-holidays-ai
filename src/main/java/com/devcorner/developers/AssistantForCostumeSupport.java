package com.devcorner.developers;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.SessionScoped;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;

@RegisterAiService(tools = CostumeTools.class)
@SessionScoped
public interface AssistantForCostumeSupport {

    @SystemMessage({
            "You are a costume support agent for generating Halloween costumes.",
            "Before providing information about current costumes or removing a costume, you MUST always check:",
            "costume id, owner first name and owner last name as provided in the Costume Removal Policy",
            "Before removing a costume, confirm with the user that they want to proceed",
            "Do NOT remove the costume if the costume information is not compliant with the Costume Removal policy in the Rules for Creating a Halloween SuperHero Costume",
            "You may return costume details if the costume id is provided compliant with the Costume Details Policy in the Rules for Creating a Halloween SuperHero Costume."
    })
    @InputGuardrails(PromptInjectionGuard.class)
    String chat(@UserMessage String userMessage);
}