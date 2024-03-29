package com.mambanegra.fitnesscenterapi.security.adapter.in.config;

import com.mambanegra.fitnesscenterapi.security.application.domain.service.InscriptionProcessor;
import com.mambanegra.fitnesscenterapi.security.application.domain.service.InscriptionTokenService;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionControllerConfiguration {

    public static final String EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME = "emailInscriptionUseCase";

    @Bean(name = EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME)
    public InscriptionUseCase emailInscriptionUseCase(InscriptionDataSource inscriptionDataSource,
                                                      InscriptionTokenService tokenService){
        return new InscriptionProcessor(inscriptionDataSource, tokenService);
    }
}
