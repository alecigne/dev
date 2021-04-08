package net.lecigne.codingkatas.springwebmvcresttemplate.animalfact;

public enum Animal {
    DOG("dog"),
    CAT("cat"),
    HORSE("horse");

    private final String type;

    Animal(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
