package com.mambanegra.fitnesscenterapi.security.application.domain.service;

import com.mambanegra.fitnesscenterapi.security.application.domain.model.User;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserCreateCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserManagementUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserModificationCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.out.UserDataSource;
import java.util.List;

public class UserManagementProcessor implements UserManagementUseCase {
    private final UserDataSource userDataSource;

    public UserManagementProcessor(UserDataSource userDataSource) {this.userDataSource = userDataSource;}

    @Override
    public void createUser(UserCreateCommand userCreateCommand) {
        userDataSource.createUser(userCreateCommand.getUser());
    }

    @Override
    public List<User> findAll() {
        return userDataSource.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userDataSource.deleteUser(id);
    }

    @Override
    public User findUserById(Long id) {
        return userDataSource.findUserById(id).orElseThrow(() -> new RuntimeException("Not found user with id:" + id));
    }

    @Override
    public void updateUser(UserModificationCommand userModificationCommand) {
        userDataSource.updateUser(userModificationCommand.getUser());
    }
}
