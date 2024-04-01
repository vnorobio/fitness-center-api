package com.mambanegra.fitnesscenterapi.security.application.port.in;

import com.mambanegra.fitnesscenterapi.security.application.domain.model.User;
import java.util.List;

public interface UserManagementUseCase {
    void createUser(UserCreateCommand userCreateCommand);
    List<User> findAll();
    void deleteUser(Long id);
    User findUserById(Long id);
    void updateUser(UserModificationCommand userModificationCommand);

}
