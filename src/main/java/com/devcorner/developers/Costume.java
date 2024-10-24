package com.devcorner.developers;

public record Costume(String id,
                      String costumeName,
                      String costumeType,
                      Long minAge,
                      Long maxAge,
                      Owner owner) {
}