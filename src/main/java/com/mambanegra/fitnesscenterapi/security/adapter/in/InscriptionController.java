package com.mambanegra.fitnesscenterapi.security.adapter.in;

import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionUseCaseConfiguration.EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/v1/inscriptions")
public class InscriptionController {

    private final InscriptionUseCase emailInscriptionProcessor;

    public InscriptionController(@Qualifier(EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME) InscriptionUseCase emailInscriptionProcessor) {this.emailInscriptionProcessor = emailInscriptionProcessor;}

    @PostMapping()
    public ResponseEntity<String> createInscription(@RequestBody String email) {
        try {
            InscriptionCommand inscriptionCommand = new InscriptionCommand(email);
            Optional<String> inscriptionResult = emailInscriptionProcessor.registerWithEmail(inscriptionCommand);
            return inscriptionResult
                    .map(token -> ResponseEntity.status(HttpStatus.CREATED).body(token))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<String> getInscription(@PathVariable String email) {
        Optional<String> inscriptionResult = emailInscriptionProcessor.findById(email);
        return inscriptionResult
                .map(token -> ResponseEntity.ok().body(token))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
