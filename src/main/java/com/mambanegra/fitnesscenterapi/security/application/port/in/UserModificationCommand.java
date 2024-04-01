package com.mambanegra.fitnesscenterapi.security.application.port.in;

import com.mambanegra.fitnesscenterapi.security.application.domain.model.User;
import java.util.Objects;

public record UserModificationCommand(Long id,
                                      String password,
                                      String firstName,
                                      String middleName,
                                      String lastName,
                                      String surname,
                                      String address,
                                      String email,
                                      String phoneNumber) {

    public UserModificationCommand {
        Objects.requireNonNull(id);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(email);
        if (id <= 0) {
            throw new IllegalArgumentException("Id value must be greater than zero.");
        }
        if (email.isBlank() || firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("Missing information email, firstName and last name are mandatory");
        }
    }

    public User getUser() {
        return new User(id, password,firstName, middleName, lastName, surname, address, email, phoneNumber);
    }

}
