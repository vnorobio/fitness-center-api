package com.mambanegra.fitnesscenterapi.security.application.port.in;

import java.util.Optional;

public interface InscriptionUseCase {
    Optional<String> registerWithEmail(InscriptionCommand command);
    Optional<String> findById(String email);
}
