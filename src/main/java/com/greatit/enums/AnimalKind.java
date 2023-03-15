package com.greatit.enums;

public enum AnimalKind {
    CAT("cat"),
    DOG("dog");

    public final String animal;

    private AnimalKind(String animal) {
        this.animal = animal;
    }

    public static AnimalKind valueOfLabel(String animal) {
        for (AnimalKind e : values()) {
            if (e.animal.equals(animal)) {
                return e;
            }
        }
        return null;
    }
}
