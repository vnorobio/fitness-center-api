package com.mambanegra.fitnesscenterapi.security.adapter.out;

import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;

public class InscriptionDataBaseAdapter implements InscriptionDataSource {
    private final InscriptionsRepository inscriptionRepository;

    public InscriptionDataBaseAdapter(InscriptionsRepository inscriptionRepository) {this.inscriptionRepository = inscriptionRepository;}

    @Override
    public void saveEmail(String email) {
        InscriptionEntity entity = new InscriptionEntity(email);
        inscriptionRepository.save(entity);
    }
}
