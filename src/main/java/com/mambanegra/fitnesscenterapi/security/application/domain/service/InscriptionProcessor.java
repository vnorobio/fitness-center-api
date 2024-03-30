package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_EMAIL_SENDER_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_TOKEN_SERVICE_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;

public class InscriptionProcessor implements InscriptionUseCase {
    private final InscriptionDataSource inscriptionDataSource;
    private final InscriptionTokenService tokenService;
    private final InscriptionEmailSender inscriptionEmailAdapter;

    public InscriptionProcessor(@Qualifier(INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME) InscriptionDataSource inscriptionDataSource,
                                @Qualifier(INSCRIPTION_TOKEN_SERVICE_BEAN_NAME) InscriptionTokenService tokenService,
                                @Qualifier(INSCRIPTION_EMAIL_SENDER_BEAN_NAME)InscriptionEmailSender inscriptionEmailAdapter) {
        this.inscriptionDataSource = inscriptionDataSource;
        this.tokenService = tokenService;
        this.inscriptionEmailAdapter = inscriptionEmailAdapter;
    }

    @Override
    public Optional<String> registerWithEmail(InscriptionCommand command) {
        String email = command.email();
        inscriptionDataSource.saveEmail(email);
        inscriptionEmailAdapter.sendInscriptionConfirmation(email);
        return Optional.of(tokenService.generateInscriptionToken(email));
    }
}
