package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

public class TokenGeneratorService {
    private final String signatureKey;

    public TokenGeneratorService(String signatureKey) {this.signatureKey = signatureKey;}

    public String generateJwtToken(String email) {
        Instant expirationInstant = LocalDate.now()
                                             .plusDays(3)
                                             .atStartOfDay(ZoneId.systemDefault())
                                             .toInstant();

        String base64KeyString = Base64.getEncoder().encodeToString(signatureKey.getBytes(StandardCharsets.UTF_8));

        Key signingKey = new SecretKeySpec(base64KeyString.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                   .subject(email)
                   .expiration(Date.from(expirationInstant))
                   .signWith(signingKey, SignatureAlgorithm.HS256)
                   .compact();
    }
}
