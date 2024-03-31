package com.mambanegra.fitnesscenterapi.security.application.domain.config;

import static com.mambanegra.fitnesscenterapi.security.adapter.out.config.FileReaderConfiguration.FILE_READER_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.application.domain.service.KeyManagerService;
import com.mambanegra.fitnesscenterapi.security.application.port.out.FileReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyManagerServiceConfiguration {
    public static final String KEY_MANAGER_BEAN_NAME = "keyManage";
    public static final String KEY_FILE_URI = "../jwtRS256.key";

    @Bean(KEY_MANAGER_BEAN_NAME)
    public KeyManagerService keyManagerService(@Qualifier(FILE_READER_BEAN_NAME) FileReader fileReader) {
        return new KeyManagerService(fileReader, KEY_FILE_URI);
    }
}
