package com.mambanegra.fitnesscenterapi.security.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InscriptionEntity {
    public InscriptionEntity(String email) {
        this.email = email;
    }

    public InscriptionEntity() {

    }

    @Id
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
