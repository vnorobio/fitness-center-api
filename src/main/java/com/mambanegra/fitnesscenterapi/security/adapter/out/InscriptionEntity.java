package com.mambanegra.fitnesscenterapi.security.adapter.out;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "inscriptions")
public class InscriptionEntity {

    @EmbeddedId
    private InscriptionEntryPK entryPK;

    public InscriptionEntity(InscriptionEntryPK entryPK) {
        this.entryPK = entryPK;
    }

    public InscriptionEntity() {

    }

    public InscriptionEntryPK getEntryPK() {
        return entryPK;
    }

    public InscriptionEntity setEntryPK(InscriptionEntryPK entryPK) {
        this.entryPK = entryPK;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InscriptionEntity entity)) {
            return false;
        }
        return Objects.equals(entryPK, entity.entryPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryPK);
    }

    @Override
    public String toString() {
        return "InscriptionEntity{" +
                "entryPK=" + entryPK +
                '}';
    }
}
