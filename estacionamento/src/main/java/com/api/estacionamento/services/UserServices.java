package com.api.estacionamento.services;

import com.api.estacionamento.model.UserModel;
import com.api.estacionamento.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> findById(UUID id ) {
        return userRepository.findById(id);
    }

    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }
}
