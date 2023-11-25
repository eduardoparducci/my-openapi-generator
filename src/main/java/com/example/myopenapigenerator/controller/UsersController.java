package com.example.myopenapigenerator.controller;

import com.example.myopenapigenerator.api.UsersApi;
import com.example.myopenapigenerator.model.UserRequest;
import com.example.myopenapigenerator.model.UserResponse;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.OffsetDateTime;
import java.util.*;

@RestController
public class UsersController implements UsersApi {

    private Map<String, UserResponse> users = new HashMap<>();

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        var user = toUserResponse(userRequest);
        users.put(user.getId(), user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        if(users.remove(id)!=null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<UserResponse>(users.values()));
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        var user = users.get(id);
        return user!=null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(String id, UserRequest userRequest) {
        var currentUser = users.get(id);

        if(currentUser!= null) {
            var newUser = toUserResponse(userRequest);
            newUser.setId(currentUser.getId());
            newUser.createdAt(currentUser.getCreatedAt());
            users.put(id, newUser);
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.notFound().build();
    }

    private UserResponse toUserResponse(UserRequest userRequest) {
         return new UserResponse()
                .id(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .email(userRequest.getEmail())
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .lastName(userRequest.getLastName())
                .password(userRequest.getPassword())
                .status(userRequest.getStatus());
    }
}
