package com.mambanegra.fitnesscenterapi.security.adapter.out;

import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import java.util.Optional;

public class InscriptionDataBaseAdapter implements InscriptionDataSource {
    private final InscriptionsRepository inscriptionRepository;

    public InscriptionDataBaseAdapter(InscriptionsRepository inscriptionRepository) {this.inscriptionRepository = inscriptionRepository;}

    @Override
    public void saveEmail(String email) {
        InscriptionEntity entity = new InscriptionEntity(email);
        inscriptionRepository.save(entity);
    }

    @Override
    public Optional<String> findById(String email) {
        return inscriptionRepository.findById(email).map(InscriptionEntity::getEmail);
    }
}
