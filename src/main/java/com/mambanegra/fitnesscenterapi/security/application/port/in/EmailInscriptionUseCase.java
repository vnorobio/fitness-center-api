package com.mambanegra.fitnesscenterapi.security.application.port.in;

import java.util.Optional;

public interface EmailInscriptionUseCase {
    Optional<String> registerWithEmail(EmailInscriptionCommand command);
}
