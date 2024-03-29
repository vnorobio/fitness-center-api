package com.mambanegra.fitnesscenterapi.security.adapter.config;

import com.mambanegra.fitnesscenterapi.security.application.domain.service.EmailInscriptionProcessor;
import com.mambanegra.fitnesscenterapi.security.application.port.in.EmailInscriptionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionControllerConfiguration {
    @Bean
    public EmailInscriptionUseCase emailInscriptionUseCase(){
        return new EmailInscriptionProcessor();
    }
}
