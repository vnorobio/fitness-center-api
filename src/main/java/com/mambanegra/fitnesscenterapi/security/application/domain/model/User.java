package com.mambanegra.fitnesscenterapi.security.application.domain.model;

public record User(Long id,
                   String firstName,
                   String middleName,
                   String lastName,
                   String surname,
                   String address,
                   String email,
                   String phoneNumber) {
}
