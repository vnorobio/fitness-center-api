package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSourceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InscriptionProcessorTest {
    public static final String REGISTRATION_TOKEN = "123456";
    public static final String TEST_EMAIL = "test@example.com";
    public static final InscriptionCommand INSCRIPTION_COMMAND = new InscriptionCommand(TEST_EMAIL);
    @Mock
    private InscriptionDataSourceAdapter mockDatasourceAdapter;

    @Mock
    private InscriptionTokenService mockTokenService;

    private InscriptionProcessor inscriptionProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        inscriptionProcessor = new InscriptionProcessor(mockDatasourceAdapter, mockTokenService);
    }

    @Test
    void registerWithEmailHappyPath() {
        when(mockTokenService.generateInscriptionToken(TEST_EMAIL)).thenReturn(REGISTRATION_TOKEN);
        doNothing().when(mockDatasourceAdapter).saveEmail(TEST_EMAIL);

        String resultToken  = inscriptionProcessor.registerWithEmail(INSCRIPTION_COMMAND).orElseGet(() -> "");

        assertEquals(REGISTRATION_TOKEN, resultToken);
        verify(mockDatasourceAdapter).saveEmail(TEST_EMAIL);
        verify(mockTokenService).generateInscriptionToken(TEST_EMAIL);
    }
}