package com.mambanegra.fitnesscenterapi.security.adapter.in.config;

import com.mambanegra.fitnesscenterapi.security.application.domain.service.InscriptionProcessor;
import com.mambanegra.fitnesscenterapi.security.application.domain.service.TokenGeneratorService;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionControllerConfiguration {

    public static final String EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME = "emailInscriptionUseCase";

    @Bean(name = EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME)
    public InscriptionUseCase emailInscriptionUseCase(InscriptionDataSource inscriptionDataSource,
                                                      TokenGeneratorService tokenService,
                                                      InscriptionEmailSender emailService){
        return new InscriptionProcessor(inscriptionDataSource, tokenService, emailService);
    }
}
