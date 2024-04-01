package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import java.security.Key;
import java.util.Optional;

public class InscriptionProcessor implements InscriptionUseCase {
    private final KeyManagerService keyManagerService;
    private final InscriptionDataSource inscriptionDataSource;
    private final TokenGeneratorService tokenService;
    private final InscriptionEmailSender inscriptionEmailAdapter;

    public InscriptionProcessor(KeyManagerService keyManagerService,
                                InscriptionDataSource inscriptionDataSource,
                                TokenGeneratorService tokenService,
                                InscriptionEmailSender inscriptionEmailAdapter) {
        this.keyManagerService = keyManagerService;
        this.inscriptionDataSource = inscriptionDataSource;
        this.tokenService = tokenService;
        this.inscriptionEmailAdapter = inscriptionEmailAdapter;
    }

    @Override
    public Optional<String> registerWithEmail(InscriptionCommand command) {
        String email = command.email();
        inscriptionDataSource.saveEmail(email);
        inscriptionEmailAdapter.sendInscriptionConfirmation(email);
        Key signingKey = keyManagerService.getPrivateKey();
        return Optional.of(tokenService.generateJwtToken(signingKey, email));
    }

    @Override
    public Optional<String> findById(String email) {
        return inscriptionDataSource.findById(email);
    }
}
