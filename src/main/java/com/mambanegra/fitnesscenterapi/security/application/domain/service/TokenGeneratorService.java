package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TokenGeneratorService {

    public String generateJwtToken(Key signingKey, String email) {
        Instant expirationInstant = LocalDate.now()
                                             .plusDays(3)
                                             .atStartOfDay(ZoneId.systemDefault())
                                             .toInstant();

        return Jwts.builder()
                   .subject(email)
                   .expiration(Date.from(expirationInstant))
                   .signWith(signingKey, SignatureAlgorithm.HS256)
                   .compact();
    }
}
