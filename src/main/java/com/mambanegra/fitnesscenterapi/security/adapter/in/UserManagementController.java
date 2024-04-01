package com.mambanegra.fitnesscenterapi.security.adapter.in;

import com.mambanegra.fitnesscenterapi.security.application.domain.model.User;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserCreateCommand;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserManagementUseCase;
import com.mambanegra.fitnesscenterapi.security.application.port.in.UserModificationCommand;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserManagementController {
    private final UserManagementUseCase userProcessor;

    public UserManagementController(UserManagementUseCase userProcessor) {this.userProcessor = userProcessor;}

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userProcessor.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userProcessor.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateCommand userCreateCommand) {
        userProcessor.createUser(userCreateCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserModificationCommand userModificationCommand) {
        userProcessor.updateUser(userModificationCommand);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userProcessor.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
