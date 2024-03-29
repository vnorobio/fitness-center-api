package com.mambanegra.fitnesscenterapi.security.application.port.in;

import java.util.Objects;

public record EmailInscriptionCommand(String email) {
    public EmailInscriptionCommand {
        if (Objects.isNull(email) || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
}
