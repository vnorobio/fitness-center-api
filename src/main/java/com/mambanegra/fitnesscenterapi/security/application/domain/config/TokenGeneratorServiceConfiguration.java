package com.mambanegra.fitnesscenterapi.security.application.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenGeneratorServiceConfiguration {

    public static final String KEY_SIGNATURE_STRING_BEAN_NAME = "keySignatureString";

    @Bean(name = KEY_SIGNATURE_STRING_BEAN_NAME)
    public String keySignatureString() {
//        TODO: implement read the private key from environment
        return "";
    }
}
