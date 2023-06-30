package com.fpbinar6.code.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.User;
import com.fpbinar6.code.models.dto.UserResponseDTO;
import com.fpbinar6.code.models.dto.UserUpdateDTO;
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
    public UserResponseDTO updateUser(UserUpdateDTO userUpdateDTO) {
        Optional<User> request = userRepository.findById(userUpdateDTO.getUserId());
        var user = request.get();
            user.setName(userUpdateDTO.getName());
            user.setEmail(userUpdateDTO.getEmail());
            user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
            var result = userRepository.save(user);
            return result.convertToResponse();
        
    }
        

    @Override
    public void deleteUserById(int id) {
        
    }
}
