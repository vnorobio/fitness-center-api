package com.mambanegra.fitnesscenterapi.security.adapter.in;

import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionUseCase;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/v1/inscription")
public class InscriptionController {

    private final EmailInscriptionUseCase emailInscriptionProcessor;

    public InscriptionController(EmailInscriptionUseCase emailInscriptionProcessor) {this.emailInscriptionProcessor = emailInscriptionProcessor;}

    @PostMapping()
    public ResponseEntity<String> createInscription(@RequestBody String email) {
        EmailInscriptionCommand inscriptionCommand = new EmailInscriptionCommand(email);
        Optional<String> inscriptionResult = emailInscriptionProcessor.registerWithEmail(inscriptionCommand);
        return inscriptionResult
                .map(token -> ResponseEntity.status(HttpStatus.CREATED).body(token))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}
