package com.mambanegra.fitnesscenterapi.security.application.domain.config;

import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_EMAIL_SENDER_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.application.domain.config.InscriptionProcessorConfiguration.INSCRIPTION_TOKEN_SERVICE_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.application.domain.config.KeyManagerServiceConfiguration.KEY_MANAGER_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.application.domain.service.InscriptionProcessor;
import com.mambanegra.fitnesscenterapi.security.application.domain.service.KeyManagerService;
import com.mambanegra.fitnesscenterapi.security.application.domain.service.TokenGeneratorService;
import com.mambanegra.fitnesscenterapi.security.application.port.in.InscriptionUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionUseCaseConfiguration {

    public static final String EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME = "emailInscriptionUseCase";

    @Bean(name = EMAIL_INSCRIPTION_USE_CASE_BEAN_NAME)
    public InscriptionUseCase emailInscriptionUseCase( @Qualifier(KEY_MANAGER_BEAN_NAME) KeyManagerService keyManagerService,
                                                       @Qualifier(INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME) InscriptionDataSource inscriptionDataSource,
                                                       @Qualifier(INSCRIPTION_TOKEN_SERVICE_BEAN_NAME) TokenGeneratorService tokenService,
                                                       @Qualifier(INSCRIPTION_EMAIL_SENDER_BEAN_NAME)InscriptionEmailSender inscriptionEmailAdapter){
        return new InscriptionProcessor(keyManagerService, inscriptionDataSource, tokenService, inscriptionEmailAdapter);
    }
}
