package com.mambanegra.fitnesscenterapi.security.application.domain.config;

import static com.mambanegra.fitnesscenterapi.security.adapter.out.config.UserDataSourceConfiguration.USER_DATA_SOURCE_BEAN_NAME;

import com.mambanegra.fitnesscenterapi.security.application.domain.service.UserManagementProcessor;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserManagementUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.out.UserDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManagementUseCaseConfiguration {
    public static final String USER_MANAGEMENT_USE_CASE_BEAN_NAME = "userManagementUseCase";

    @Bean(USER_MANAGEMENT_USE_CASE_BEAN_NAME)
    public UserManagementUseCase userManagementUseCase(@Qualifier(USER_DATA_SOURCE_BEAN_NAME)UserDataSource userDataSource) {
        return new UserManagementProcessor(userDataSource);
    }
}
