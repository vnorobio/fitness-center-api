package com.mambanegra.fitnesscenterapi.security.adapter.out.config;

import com.mambanegra.fitnesscenterapi.security.adapter.out.UserDataBaseAdapter;
import com.mambanegra.fitnesscenterapi.security.adapter.out.UsersRepository;
import com.mambanegra.fitnesscenterapi.security.application.port.out.UserDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDataSourceConfiguration {
    public static final String USER_DATA_SOURCE_BEAN_NAME = "UserDataSource";

    @Bean(USER_DATA_SOURCE_BEAN_NAME)
    public UserDataSource userDataSource(UsersRepository repository) {
        return new UserDataBaseAdapter(repository);
    }
}
