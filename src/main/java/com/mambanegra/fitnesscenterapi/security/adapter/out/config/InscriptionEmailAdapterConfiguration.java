package com.mambanegra.fitnesscenterapi.security.adapter.out.config;

import java.net.http.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InscriptionEmailAdapterConfiguration {
    public static final String INSCRIPTION_BASE_URL_BEAN_NAME = "inscriptionEmailBaseUrl";
    public static final String INSCRIPTION_URI_BEAN_NAME = "inscriptionEmailSendUri";
    public static final String INSCRIPTION_ACCESS_TOKEN_BEAN_NAME = "inscriptionAccessToken";
    public static final String HTTP_CLIENT_BEAN_NAME = "httpClient";

    @Bean(INSCRIPTION_BASE_URL_BEAN_NAME)
    public  String inscriptionEmailBaseUrl() {
        return "https://app-ebon-alpha.vercel.app";
    }

    @Bean(INSCRIPTION_URI_BEAN_NAME)
    public  String inscriptionEmailSendUri() {
        return "/api/webhook/inscriptions";
    }

    @Bean(INSCRIPTION_ACCESS_TOKEN_BEAN_NAME)
    public  String inscriptionAccessToken() {
        return "eyJhbGciOiJIUzI1NiJ9.eyJ1cm46YmxhY2ttYW1iYTpjbGFpbSI6dHJ1ZSwiaWF0IjoxNzExNzUyNTI0LCJpc3MiOiJ1cm46YmxhY2ttYW1iYTppc3N1ZXIiLCJhdWQiOiJ1cm46YmxhY2ttYW1iYTphdWRpZW5jZSIsImV4cCI6MTg2OTU0MDUyNH0.vaWBb7zu3M8X9mbAqeyM3tKuImuM_LOj1O1OZz673F4";
    }

    @Bean(HTTP_CLIENT_BEAN_NAME)
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
