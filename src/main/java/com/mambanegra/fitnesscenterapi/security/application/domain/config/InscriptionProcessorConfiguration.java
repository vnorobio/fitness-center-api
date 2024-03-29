package com.mambanegra.fitnesscenterapi.security.application.domain.config;

import com.mambanegra.fitnesscenterapi.security.adapter.out.InscriptionDataBaseAdapter;
import com.mambanegra.fitnesscenterapi.security.application.domain.service.InscriptionTokenService;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionProcessorConfiguration {

    public static final String INSCRIPTION_TOKEN_SERVICE_BEAN_NAME = "inscriptionTokenService";
    public static final String INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME = "inscriptionDataSourceAdapter";

    @Bean(name = INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME)
    public InscriptionDataSource inscriptionDataSourceAdapter() {
        return new InscriptionDataBaseAdapter();
    }

    @Bean(name = INSCRIPTION_TOKEN_SERVICE_BEAN_NAME)
    public InscriptionTokenService inscriptionTokenService() {
        return new InscriptionTokenService();
    }
}
