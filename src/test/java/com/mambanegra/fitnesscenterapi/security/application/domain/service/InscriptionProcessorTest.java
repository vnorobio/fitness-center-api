package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InscriptionProcessorTest {
    public static final String REGISTRATION_TOKEN = "123456";
    public static final String TEST_EMAIL = "test@example.com";
    public static final InscriptionCommand INSCRIPTION_COMMAND = new InscriptionCommand(TEST_EMAIL);
    private static final String SIGNATURE_KEY_STRING = """
            TkXJxU4+9VoEq2IBlI6BrbpZbpa5GxTOhbicbgpm3mFcMtljLqLHXQUgBNjuShqy
            N0O+XLrbW5C9Ryo4ztp2RjynA6yb2X96De0QSsf32iLyMkt30BCHJ3t6JpkCAwEA
            AQKCAgAuA5jftVN/34rL9b9G7J9wJL9Oenwl/5lVA9WfQUvrqo6FBJ8IyjYARmKA
            vUaQ0D+mPqGWmuTQsUGL/Zn8ANKYAGFjVfaOVZjnX3VGCMRoMK+nxL+2HpHolSe5
            lteK5VrdqAnFkUXno38RSSPfGnUjDxkudTJDckkNyTG34wpwE
            """;
    public static final SecretKeySpec SIGNATURE_KEY = new SecretKeySpec(SIGNATURE_KEY_STRING.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    @Mock
    private InscriptionDataSource mockDatasourceAdapter;

    @Mock
    private TokenGeneratorService mockTokenService;

    @Mock
    InscriptionEmailSender mockEmailSender;

    @Mock
    KeyManagerService keyManagerService;

    private InscriptionProcessor inscriptionProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        inscriptionProcessor = new InscriptionProcessor(keyManagerService, mockDatasourceAdapter, mockTokenService, mockEmailSender);
    }

    @Test
    void registerWithEmailHappyPath() {
        when(mockTokenService.generateJwtToken(SIGNATURE_KEY, TEST_EMAIL)).thenReturn(REGISTRATION_TOKEN);
        when(keyManagerService.getPrivateKey()).thenReturn(SIGNATURE_KEY);
        doNothing().when(mockDatasourceAdapter).saveEmail(TEST_EMAIL);
        doNothing().when(mockEmailSender).sendInscriptionConfirmation(TEST_EMAIL);
        String resultToken  = inscriptionProcessor.registerWithEmail(INSCRIPTION_COMMAND).orElseGet(() -> "");

        assertEquals(REGISTRATION_TOKEN, resultToken);
        verify(mockDatasourceAdapter).saveEmail(TEST_EMAIL);
        verify(mockEmailSender).sendInscriptionConfirmation(TEST_EMAIL);
        verify(mockTokenService).generateJwtToken(SIGNATURE_KEY, TEST_EMAIL);
    }
}