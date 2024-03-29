package com.mambanegra.fitnesscenterapi.security.application.port.in;

import java.util.Objects;
import org.apache.commons.validator.routines.EmailValidator;

public record EmailInscriptionCommand(String email) {
    public EmailInscriptionCommand {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (Objects.isNull(email) || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (!emailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
