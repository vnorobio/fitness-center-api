package com.mambanegra.fitnesscenterapi.security.adapter.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class InscriptionControllerTest {

    public static final String VALID_EMAIL = "valid.email@server.com";
    public static final String INVALID_EMAIL = "user-name@domain.com.";
    public static final InscriptionCommand INSCRIPTION_COMMAND = new InscriptionCommand(VALID_EMAIL);
    public static final String REGISTRATION_TOKEN = "valid-registration-token";
    @Mock
    public InscriptionUseCase inscriptionUseCase;

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

    @Test
    void createInscriptionReturnBadRequestCodeWhenEmptyEmail() {
        ResponseEntity<String> response = inscriptionController.createInscription("");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verifyNoInteractions(inscriptionUseCase);
    }

    @Test
    void createInscriptionReturnBadRequestCodeWhenInvalidEmail() {
        ResponseEntity<String> response = inscriptionController.createInscription(INVALID_EMAIL);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verifyNoInteractions(inscriptionUseCase);
    }
}