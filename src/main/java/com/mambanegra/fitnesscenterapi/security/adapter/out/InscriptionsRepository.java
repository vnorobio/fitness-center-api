package com.mambanegra.fitnesscenterapi.security.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionsRepository extends JpaRepository<InscriptionEntity, String> {

}
