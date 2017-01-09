package com.example;

import javax.persistence.Embeddable;

@Embeddable
public final class Embedded {
    private String name;

    public Embedded(String name) {
        this.name = name;
    }

    Embedded() {
        // for JPA
    }

    public String getName() {
        return name;
    }
}
