package com.mambanegra.fitnesscenterapi.security.adapter.out;

import com.mambanegra.fitnesscenterapi.security.adapter.out.UserEntity;
import com.mambanegra.fitnesscenterapi.security.adapter.out.UsersRepository;
import com.mambanegra.fitnesscenterapi.security.application.domain.model.User;
import com.mambanegra.fitnesscenterapi.security.application.port.out.UserDataSource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDataBaseAdapter implements UserDataSource {
    private final UsersRepository userRepository;

    public UserDataBaseAdapter(UsersRepository userRepository) {this.userRepository = userRepository;}
    @Override
    public void createUser(User user) {
        UserEntity entity = mapToEntity(user);
        userRepository.save(entity);
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream()
                                   .map(this::mapToModel)
                                   .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id).map(this::mapToModel);
    }

    @Override
    public void updateUser(User user) {
        UserEntity entity = mapToEntity(user);
        userRepository.save(entity);
    }

    private UserEntity mapToEntity(User user) {
        return new UserEntity(
                user.id(),
                user.password(),
                user.firstName(),
                user.middleName(),
                user.lastName(),
                user.surname(),
                user.address(),
                user.email(),
                user.phoneNumber());
    }

    private User mapToModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getSurname(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }
}
