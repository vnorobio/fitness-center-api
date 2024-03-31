package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenGeneratorServiceTest {

    private final String SIGNATURE_KEY_STRING = """
MIIJKAIBAAKCAgEAwTjy8sQiAahJzguy+6bOlzR2vohZ5VMiovcrVoHuIvqS3VOb
PmaQWnwVkmRoObf/tnygX/9mZYCWEvsHjiTy+SSmhH3wjahtyLppwe6ew0+RYuZS
KPI9CaVc1hEtDfDW/jGw/0dtC4V7YQbLGsqUZ80nq9v9ttG8UqRQ6rSAtylCerNE
dzywmX9tw4+3s2R6GWZHszVhHIgJnK8gLgauni+uUmbKfvp5b6m4qZPKBgtKbBEW
JfV0fzCVaEZ9uY2xpxTov2/q4CewAS0leip/I6dk3D1L0YEXj1KWsLWOT269kIuj
P0O8GqwABMxAZ+jksDjH4TL78jIazN/CbmvQTBdt0EtYK+Vk7XlbMXPw0iZQhQBb
wuGhywTPiVQTcBXpom2ApzWZvL7xd1P5HPeYAkYI5BeETWxOyY/6hhShWDAfxsjU
uxHID5RrXQaL+nrQElYqn+63e1urM8pOgPNdgWRcYthRRwcq8dFbspHI684i90UP
2WRpsZi1EeqHsOFupFJI9BxnIhvz1OnZLomi/hTw/0LBMO/pP6qSzK01jDj+9yza
TkXJxU4+9VoEq2IBlI6BrbpZbpa5GxTOhbicbgpm3mFcMtljLqLHXQUgBNjuShqy
N0O+XLrbW5C9Ryo4ztp2RjynA6yb2X96De0QSsf32iLyMkt30BCHJ3t6JpkCAwEA
AQKCAgAuA5jftVN/34rL9b9G7J9wJL9Oenwl/5lVA9WfQUvrqo6FBJ8IyjYARmKA
vUaQ0D+mPqGWmuTQsUGL/Zn8ANKYAGFjVfaOVZjnX3VGCMRoMK+nxL+2HpHolSe5
lteK5VrdqAnFkUXno38RSSPfGnUjDxkudTJDckkNyTG34wpwE
            """;


    private TokenGeneratorService tokenGeneratorService;

    @BeforeEach
    void setUp() {
        tokenGeneratorService = new TokenGeneratorService(SIGNATURE_KEY_STRING);
    }

    @Test
    void shouldGenerateJwtToken() {
        // Given
        String expectedEmail = "test@gmail.com";

        String strBase64 = Base64.getEncoder().encodeToString(SIGNATURE_KEY_STRING.getBytes(StandardCharsets.UTF_8));
        Key signingKey = new SecretKeySpec(strBase64.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

        // When
        String token = tokenGeneratorService.generateJwtToken(expectedEmail);

        // Then
        assertNotNull(token);
        String actualEmail = Jwts.parser()
                                 .setSigningKey(signingKey)
                                 .build().parseClaimsJws(token).getBody().getSubject();

        assertEquals(expectedEmail, actualEmail);
    }

}
