package com.mambanegra.fitnesscenterapi.security.adapter.out;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class InscriptionEmailAdapterTest {

    private static final String INSCRIPTION_URI  = "https://server.com/some/uri";
    private static final String INSCRIPTION_ACCESS_TOKEN  = "some-token";
    public static final String EMAIL_ADDRESS = "test@test.com";
    private static final String AUTHORIZATION_TOKEN_HEADER = "x-access-token";
    public static final HttpRequest INSCRIPTION_REQUEST = HttpRequest.newBuilder()
                                                                     .timeout(Duration.ofMillis(500))
                                                                     .header(AUTHORIZATION_TOKEN_HEADER, INSCRIPTION_ACCESS_TOKEN)
                                                                     .header("Content-Type", "application/json")
                                                                     .uri(URI.create(INSCRIPTION_URI))
                                                                     .POST(HttpRequest.BodyPublishers.ofString(EMAIL_ADDRESS))
                                                                     .build();
    public static final HttpResponse.BodyHandler<String> HTTP_RESPONSE_BODY_HANDLER = HttpResponse.BodyHandlers.ofString();


    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse httpResponse;

    private InscriptionEmailAdapter emailAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        emailAdapter = new InscriptionEmailAdapter(INSCRIPTION_URI, INSCRIPTION_ACCESS_TOKEN, httpClient);
    }

    @Test
    @DisplayName("Should send inscription confirmation successfully")
    void sendInscriptionConfirmationSuccessTest() throws Exception {
        when(httpResponse.statusCode()).thenReturn(HttpStatus.OK.value());
        when(httpResponse.body()).thenReturn("OK");
        when(httpClient.send(INSCRIPTION_REQUEST, HTTP_RESPONSE_BODY_HANDLER)).thenReturn(httpResponse);

        emailAdapter.sendInscriptionConfirmation(EMAIL_ADDRESS);

        verify(httpResponse, times(2)).statusCode();
        verify(httpClient, times(1)).send(INSCRIPTION_REQUEST, HTTP_RESPONSE_BODY_HANDLER);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenHttpClientThrowsIOException() throws Exception {
        doThrow(IOException.class).when(httpClient).send(INSCRIPTION_REQUEST, HTTP_RESPONSE_BODY_HANDLER);

        assertThrows(RuntimeException.class, () -> emailAdapter.sendInscriptionConfirmation(EMAIL_ADDRESS));
        verify(httpClient, times(1)).send(INSCRIPTION_REQUEST, HTTP_RESPONSE_BODY_HANDLER);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenHttpClientThrowsInterruptedException() throws Exception {
        doThrow(InterruptedException.class).when(httpClient).send(INSCRIPTION_REQUEST, HTTP_RESPONSE_BODY_HANDLER);

        assertThrows(RuntimeException.class, () -> emailAdapter.sendInscriptionConfirmation(EMAIL_ADDRESS));
        verify(httpClient, times(1)).send(INSCRIPTION_REQUEST, HTTP_RESPONSE_BODY_HANDLER);
    }
}