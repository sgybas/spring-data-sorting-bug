package com.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class MyEntity {

    @Id
    private String id;

    private Embedded embedded;

    public MyEntity(Embedded embedded) {
        this.id = embedded.getName();
        this.embedded = embedded;
    }

    MyEntity() {
        // for JPA
    }

    public String getId() {
        return id;
    }

    public Embedded getEmbedded() {
        return embedded;
    }
}
