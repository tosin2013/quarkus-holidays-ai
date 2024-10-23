package com.devcorner.developers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/halloween")
public class HalloweenQuestionResource {

    @Inject
    Assistant assistant;

    @GET
    @Path("/costume")
    @Produces(MediaType.TEXT_PLAIN)
    public String whatShouldMyHalloweenCostumeBe() {
        return assistant.chat("What should my halloween costume be?");
    }
}
