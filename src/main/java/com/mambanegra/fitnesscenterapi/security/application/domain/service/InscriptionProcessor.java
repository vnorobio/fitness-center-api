package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_TOKEN_SERVICE_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;

public class InscriptionProcessor implements InscriptionUseCase {
    private final InscriptionDataSource inscriptionDataSource;
    private final InscriptionTokenService tokenService;

    public InscriptionProcessor(@Qualifier(INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME) InscriptionDataSource inscriptionDataSource,
                                @Qualifier(INSCRIPTION_TOKEN_SERVICE_BEAN_NAME) InscriptionTokenService tokenService) {
        this.inscriptionDataSource = inscriptionDataSource;
        this.tokenService = tokenService;
    }

    @Override
    public Optional<String> registerWithEmail(InscriptionCommand command) {
        String email = command.email();
        inscriptionDataSource.saveEmail(email);
        return Optional.of(tokenService.generateInscriptionToken(email));
    }
}
