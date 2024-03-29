package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;

public class InscriptionTokenService {
    public String generateInscriptionToken(String email) {
        // TODO: fix signing key
        //  We need a signing key, so we'll create one just for this example. Usually
        //  the key would be read from your application configuration instead.
        SecretKey key = Jwts.SIG.HS256.key().build();
        Instant expirationInstant = LocalDate.now()
                                             .plusDays(3)
                                             .atStartOfDay(ZoneId.systemDefault())
                                             .toInstant();
        return Jwts.builder()
                   .subject(email)
                   .expiration(Date.from(expirationInstant))
                   .signWith(key)
                   .compact();
    }
}
