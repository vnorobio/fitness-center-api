package com.mambanegra.fitnesscenterapi.security.adapter.out;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionsRepository extends JpaRepository<InscriptionEntity, InscriptionEntryPK> {

    Optional<InscriptionEntity> findByEntryPK(InscriptionEntryPK entryPK);
}
