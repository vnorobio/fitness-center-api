package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionUseCase;
import java.util.Optional;

public class EmailInscriptionProcessor implements EmailInscriptionUseCase {
    @Override
    public Optional<String> registerWithEmail(EmailInscriptionCommand command) {
//        TODO: implement email registration logic
        return Optional.empty();
    }
}
