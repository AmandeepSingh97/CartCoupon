package repositories;

import models.Attribute;

import java.util.UUID;

public class AttributeRepository {

    public static Attribute createAttribute(String name, String value) {
        final Attribute attribute = new Attribute(UUID.randomUUID().toString(), name, value);
        return attribute;
    }

}
