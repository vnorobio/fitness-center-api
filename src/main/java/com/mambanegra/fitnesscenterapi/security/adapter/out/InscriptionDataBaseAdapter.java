package com.mambanegra.fitnesscenterapi.security.adapter.out;

import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import java.util.Optional;

public class InscriptionDataBaseAdapter implements InscriptionDataSource {
    private final InscriptionsRepository inscriptionRepository;

    public InscriptionDataBaseAdapter(InscriptionsRepository inscriptionRepository) {this.inscriptionRepository = inscriptionRepository;}

    @Override
    public void saveEmail(String email) {
        InscriptionEntryPK entryPK = new InscriptionEntryPK(email);
        InscriptionEntity entity = new InscriptionEntity(entryPK);
        inscriptionRepository.save(entity);
    }

    @Override
    public Optional<String> findById(InscriptionEntryPK entryPK) {
        return inscriptionRepository.findByEntryPK( entryPK).map(inscriptionEntity -> inscriptionEntity.getEntryPK().getEmail());
    }
}
