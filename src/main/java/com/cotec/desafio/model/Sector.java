package com.cotec.desafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sector extends BasicEntity {

    @Column(unique = true)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
