package com.fpbinar6.code.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.User;
import com.fpbinar6.code.repository.UserRepository;
import com.fpbinar6.code.services.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        
    }
}
