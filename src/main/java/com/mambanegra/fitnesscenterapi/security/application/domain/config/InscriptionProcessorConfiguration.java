package com.mambanegra.fitnesscenterapi.security.application.domain.config;

import static com.mambanegra.fitnesscenterapi.security.adapter.out.config.InscriptionEmailAdapterConfiguration.HTTP_CLIENT_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.adapter.out.config.InscriptionEmailAdapterConfiguration.INSCRIPTION_ACCESS_TOKEN_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.adapter.out.config.InscriptionEmailAdapterConfiguration.INSCRIPTION_BASE_URL_BEAN_NAME;
import static com.mambanegra.fitnesscenterapi.security.adapter.out.config.InscriptionEmailAdapterConfiguration.INSCRIPTION_URI_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.adapter.out.InscriptionDataBaseAdapter;
import com.mambanegra.fitnesscenterapi.security.adapter.out.InscriptionEmailAdapter;
import com.mambanegra.fitnesscenterapi.security.application.domain.service.InscriptionTokenService;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionDataSource;
import com.mambanegra.fitnesscenterapi.security.application.port.out.InscriptionEmailSender;
import java.net.http.HttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionProcessorConfiguration {

    public static final String INSCRIPTION_TOKEN_SERVICE_BEAN_NAME = "inscriptionTokenService";
    public static final String INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME = "inscriptionDataSourceAdapter";
    public static final String INSCRIPTION_EMAIL_SENDER_BEAN_NAME = "inscriptionEmailSender";

    @Bean(name = INSCRIPTION_DATA_SOURCE_ADAPTER_BEAN_NAME)
    public InscriptionDataSource inscriptionDataSourceAdapter() {
        return new InscriptionDataBaseAdapter();
    }

    @Bean(name = INSCRIPTION_TOKEN_SERVICE_BEAN_NAME)
    public InscriptionTokenService inscriptionTokenService() {
        return new InscriptionTokenService();
    }

    @Bean(name = INSCRIPTION_EMAIL_SENDER_BEAN_NAME)
    public InscriptionEmailSender inscriptionEmailSender(@Qualifier(INSCRIPTION_BASE_URL_BEAN_NAME) String baseUrl,
                                                         @Qualifier(INSCRIPTION_URI_BEAN_NAME) String resourceUri,
                                                         @Qualifier(INSCRIPTION_ACCESS_TOKEN_BEAN_NAME) String accessToken,
                                                         @Qualifier(HTTP_CLIENT_BEAN_NAME) HttpClient httpClient) {
        String fullResourceUrl = baseUrl.concat(resourceUri);
        return new InscriptionEmailAdapter(fullResourceUrl, accessToken, httpClient);
    }
}
