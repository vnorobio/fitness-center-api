package com.mambanegra.fitnesscenterapi.security.adapter.out.config;

import com.mambanegra.fitnesscenterapi.security.adapter.out.FileReaderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileReaderConfiguration {
    public static final String FILE_READER_BEAN_NAME = "fileReader";

    @Bean(FILE_READER_BEAN_NAME)
    public FileReaderAdapter fileReader() {
        return new FileReaderAdapter();
    }
}
