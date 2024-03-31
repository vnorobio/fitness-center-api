package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenGeneratorServiceTest {

    public static final String TEST_EMAIL = "test@gmail.com";
    private static final String SIGNATURE_KEY_STRING = """
            TkXJxU4+9VoEq2IBlI6BrbpZbpa5GxTOhbicbgpm3mFcMtljLqLHXQUgBNjuShqy
            N0O+XLrbW5C9Ryo4ztp2RjynA6yb2X96De0QSsf32iLyMkt30BCHJ3t6JpkCAwEA
            AQKCAgAuA5jftVN/34rL9b9G7J9wJL9Oenwl/5lVA9WfQUvrqo6FBJ8IyjYARmKA
            vUaQ0D+mPqGWmuTQsUGL/Zn8ANKYAGFjVfaOVZjnX3VGCMRoMK+nxL+2HpHolSe5
            lteK5VrdqAnFkUXno38RSSPfGnUjDxkudTJDckkNyTG34wpwE
            """;
    private static final String BASE_64_STRING = Base64.getEncoder().encodeToString(SIGNATURE_KEY_STRING.getBytes(StandardCharsets.UTF_8));
    public static final SecretKeySpec SIGNATURE_KEY = new SecretKeySpec(BASE_64_STRING.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());


    private TokenGeneratorService tokenGeneratorService;

    @BeforeEach
    void setUp() {
        tokenGeneratorService = new TokenGeneratorService();
    }

    @Test
    void shouldGenerateJwtToken() {
        String token = tokenGeneratorService.generateJwtToken(SIGNATURE_KEY, TEST_EMAIL);

        assertNotNull(token);
        String actualEmail = Jwts.parser()
                                 .setSigningKey(SIGNATURE_KEY)
                                 .build().parseClaimsJws(token).getBody().getSubject();

        assertEquals(TEST_EMAIL, actualEmail);
    }

}
