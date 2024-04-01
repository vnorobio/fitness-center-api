package com.mambanegra.fitnesscenterapi.security.application.port.out;

import com.mambanegra.fitnesscenterapi.security.adapter.out.InscriptionEntity;
import com.mambanegra.fitnesscenterapi.security.adapter.out.InscriptionEntryPK;
import java.util.Optional;

public interface InscriptionDataSource {
    void saveEmail(String email);
    Optional<String> findById(InscriptionEntryPK entryPK);
}
