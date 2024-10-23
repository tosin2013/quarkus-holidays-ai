package com.devcorner.developers;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/poem")
public class DeveloperResource {

    @Inject
    private AssistantWithMemory ai;

    @GET
    @Path("/memory")
    @Produces(MediaType.TEXT_PLAIN)
    public String memory() {
        String msg1 = "How do I write a poem about my favorite Halloween costume?";

        String response = "[User]: " + msg1 + "\n\n" +
                "[LLM]: "+ ai.chat(1, msg1) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg2 = "Create a poem about a random costume. " +
                "Be short, 15 lines of maximum.";

        response += "[User]: " + msg2 + "\n\n"+
                "[LLM]: "+ ai.chat(1, msg2);

        return response;

    }

    @GET
    @Path("/guess")
    @Produces(MediaType.TEXT_PLAIN)
    public String guess() {
        String msg1FromUser1 = "Hello, my name is Pete and my Halloween costume is Santa Claus";

        String response = "[User1]: " + msg1FromUser1 + "\n\n" +
                "[LLM]: " + ai.chat(1, msg1FromUser1) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg1FromUser2 = "Hi, I'm Jeremy and my Halloween costume is a rock and roll star";

        response += "[User2]: " + msg1FromUser2 + "\n\n" +
                "[LLM]: " + ai.chat(2, msg1FromUser2) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg2FromUser2 = "What is my name?";

        response += "[User2]: " + msg2FromUser2 + "\n\n" +
                "[LLM]: " + ai.chat(2, msg2FromUser2) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        String msg2FromUser1 = "Please write me a 15 line poem about my Halloween costume?";

        response += "[User1]: " + msg2FromUser1 + "\n\n" +
                "[LLM]: " + ai.chat(1, msg2FromUser1) + "\n\n\n" +
                "------------------------------------------\n\n\n";

        return response;
    }

}