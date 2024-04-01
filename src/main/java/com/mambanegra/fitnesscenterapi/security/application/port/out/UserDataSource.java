package com.mambanegra.fitnesscenterapi.security.application.port.out;

import com.mambanegra.fitnesscenterapi.security.application.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDataSource {
    void createUser(User user);
    List<User> findAll();
    void deleteUser(User user);
    Optional<User> findUserById(Long id);
    void updateUser(User user);
}
