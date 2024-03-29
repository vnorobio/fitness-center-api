package com.mambanegra.fitnesscenterapi.security.adapter.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionUseCase;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class InscriptionControllerTest {

    public static final String VALID_EMAIL = "valid.email@server.com";
    public static final EmailInscriptionCommand INSCRIPTION_COMMAND = new EmailInscriptionCommand(VALID_EMAIL);
    public static final String REGISTRATION_TOKEN = "valid-registration-token";
    @Mock
    public EmailInscriptionUseCase inscriptionUseCase;

    private InscriptionController inscriptionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inscriptionController = new InscriptionController(inscriptionUseCase);
    }

    @Test
    void createInscriptionReturnValidTokenHappyPath() {
        when(inscriptionUseCase.registerWithEmail(INSCRIPTION_COMMAND)).thenReturn(Optional.of(REGISTRATION_TOKEN));
        ResponseEntity<String> response = inscriptionController.createInscription(VALID_EMAIL);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(REGISTRATION_TOKEN, response.getBody());
        verify(inscriptionUseCase, times(1)).registerWithEmail(INSCRIPTION_COMMAND);
    }

    @Test
    void createInscriptionReturnInternalServerErrorCode() {
        when(inscriptionUseCase.registerWithEmail(INSCRIPTION_COMMAND)).thenReturn(Optional.empty());
        ResponseEntity<String> response = inscriptionController.createInscription(VALID_EMAIL);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(inscriptionUseCase, times(1)).registerWithEmail(INSCRIPTION_COMMAND);
    }
}