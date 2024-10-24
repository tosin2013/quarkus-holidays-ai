package com.devcorner.developers;

import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CostumeService {

  private static final Logger LOG = Logger.getLogger(CostumeService.class.getName());

    @Inject
    @ConfigProperty(name = "costume")
    Map<String, String> costumes;

    public Costume getCostumeDetails(String id, String ownerFirstName, String ownerLastName) {
        LOG.info("Id " + id + " firstName " + ownerFirstName + " lastName " + ownerLastName);

        ensureExists(id, ownerFirstName, ownerLastName);
        String costumeName = costumes.get("name");
        String costumeType = costumes.get("type");
        Long minAge = Long.parseLong(costumes.get("minage"));
        Long maxAge = Long.parseLong(costumes.get("maxage"));

        // Retrieval from DB mocking
        Owner owner = new Owner(ownerFirstName, ownerLastName);
        return new Costume(id, costumeName, costumeType, minAge, maxAge, owner);
    }

    public void removeCostume(String id, String ownerFirstName, String ownerLastName) {
        ensureExists(id, ownerFirstName, ownerLastName);

        // TODO add logic to double check costume in case the LLM got it
        // wrong.
        costumes.remove(id);

        // throw new BookingCannotBeCancelledException(bookingNumber);
    }

    private void ensureExists(String id, String ownerFirstName, String ownerLastName) {
        // Check mocking
        LOG.info("ensureExists -> Id " + costumes.get("id") + " firstName " + costumes.get("ownerfirstname") + " lastName " + costumes.get("ownerlastname"));
        if (!(id.equals(costumes.get("id"))
                && ownerFirstName.toLowerCase().equals(costumes.get("ownerfirstname").toLowerCase())
                && ownerLastName.toLowerCase().equals(costumes.get("ownerlastname").toLowerCase()))) {
            throw new CostumeNotFoundException(id);
        }
    }
}

class CostumeNotFoundException extends RuntimeException {

    public CostumeNotFoundException(String id) {
        super("Costume " + id + " not found");
    }
}

class CostumeCannotBeRemovedException extends RuntimeException {

    public CostumeCannotBeRemovedException(String id) {
        super("Costume " + id + " cannot be removed");
    }
}
