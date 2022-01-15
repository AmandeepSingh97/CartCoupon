package models;

import repositories.AttributeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

    String id;
    Attribute price;
    private Map<String,Attribute> attributes;

    public Item(String id, String price) {
        this.id = id;
        this.price = AttributeRepository.createAttribute("Price", price);
        attributes = new HashMap<>();
        attributes.put("Price", this.price);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Attribute getPrice() {
        return price;
    }

    public void setPrice(Attribute price) {
        this.price = price;
    }

    public Map<String,Attribute> getAttributes() {
        return attributes;
    }

    public Item addAttribute(Attribute attribute) {
        this.attributes.put(attribute.name, attribute);
        this.attributes.put(attribute.name, attribute);
        return this;
    }

    public Item addAttributes(List<Attribute> attributes) {
        for (Attribute attribute: attributes) {
            addAttribute(attribute);
        }
        return this;
    }

    public String getAttributeValue(String attributeName) {
        return attributes.get(attributeName).value;
    }
}
