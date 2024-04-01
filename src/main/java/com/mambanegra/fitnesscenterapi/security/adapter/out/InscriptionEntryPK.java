package com.mambanegra.fitnesscenterapi.security.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InscriptionEntryPK implements Serializable {
    @Column(name = "email")
    private String email;

    public InscriptionEntryPK() {

    }

    public InscriptionEntryPK(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public InscriptionEntryPK setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InscriptionEntryPK that)) {
            return false;
        }
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "InscriptionEntryPK{" +
                "email='" + email + '\'' +
                '}';
    }
}
