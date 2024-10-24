package com.devcorner.developers;

import jakarta.inject.Singleton;

import dev.langchain4j.agent.tool.Tool;

@Singleton
public class CostumeTools {

    private final CostumeService costumeService;

    public CostumeTools(CostumeService costumeService) {
        this.costumeService = costumeService;
    }

    @Tool
    public Costume getCostumeDetails(String id, String ownerFirstName, String ownerLastName) {
        return costumeService.getCostumeDetails(id, ownerFirstName, ownerLastName);
    }

    @Tool
    public void removeCostume(String id, String ownerFirstName, String ownerLastName) {
        costumeService.removeCostume(id, ownerFirstName, ownerLastName);
    }
}