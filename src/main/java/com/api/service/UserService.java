package com.api.service;

import com.api.model.User;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Long, User> users = new HashMap<>();
    private final Faker faker = new Faker();
    private long idCounter = 1;

    public UserService() {
        for (int i = 0; i < 5; i++) {
            generateFakeUser();
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public User createUser(User user) {
        user.setId(idCounter++);
        if (user.isValid()) {
            users.put(user.getId(), user);
            return user;
        } else
            return null;
    }

    public Boolean userExists(long id) {
        return users.containsKey(id);
    }

    public User updateUser(Long id, User updatedUser) {
        if (updatedUser.isPartialValid()) {
            updatedUser.setId(id);
            users.put(id, updatedUser);
            return updatedUser;
        } else
            return null;
    }

    public User patchUser(Long id, User partialUser) {
        User existingUser = users.get(id);
        if (existingUser != null) {
            if (partialUser.getName() != null) {
                existingUser.setName(partialUser.getName());
            }
            if (partialUser.getEmail() != null) {
                existingUser.setEmail(partialUser.getEmail());
            }
            if (partialUser.getPhone() != null) {
                existingUser.setPhone(partialUser.getPhone());
            }
            if (partialUser.getAddress() != null) {
                existingUser.setAddress(partialUser.getAddress());
            }
            users.put(id, existingUser);
            return existingUser;
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        return users.remove(id) != null;
    }

    public void generateFakeUser() {
        User user = new User();
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPhone(faker.phoneNumber().phoneNumber());
        user.setAddress(faker.address().fullAddress());
        createUser(user);
    }
}
