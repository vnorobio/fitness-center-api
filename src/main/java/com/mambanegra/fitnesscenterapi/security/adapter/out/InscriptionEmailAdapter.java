package com.mambanegra.fitnesscenterapi.security.adapter.out;

import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import org.springframework.http.HttpStatus;

public class InscriptionEmailAdapter implements InscriptionEmailSender {

    private static final String AUTHORIZATION_TOKEN_HEADER = "x-access-token";


    private final HttpClient httpClient;
    private final String resourceUri;
    private final String accessToken;
    private final Base64.Encoder  encoder;

    public InscriptionEmailAdapter(String resourceUri, String accessToken, HttpClient httpClient) {
        this.resourceUri = resourceUri;
        this.accessToken = accessToken;
        this.httpClient = httpClient;
        this.encoder = Base64.getEncoder();
    }
    @Override
    public void sendInscriptionConfirmation(String email) {
        try {
            String encodedEmail = encoder.encodeToString(email.getBytes(StandardCharsets.UTF_8));
            HttpRequest request = prepareInscriptionEmailRequest(encodedEmail);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != HttpStatus.OK.value()) {
                throw new RuntimeException("Failed to send inscription confirmation email");
            }
            response.statusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted operation trying to send inscription confirmation email", e);
        }
    }

    private HttpRequest prepareInscriptionEmailRequest(String email) {
        return HttpRequest.newBuilder()
                          .timeout(Duration.ofMillis(500000))
                          .header(AUTHORIZATION_TOKEN_HEADER, accessToken)
                          .header("Content-Type", "application/json")
                          .uri(URI.create(resourceUri))
                          .POST(HttpRequest.BodyPublishers.ofString(email))
                          .build();
    }
}
