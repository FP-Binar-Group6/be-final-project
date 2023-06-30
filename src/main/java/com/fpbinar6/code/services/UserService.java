package com.fpbinar6.code.services;

import com.fpbinar6.code.models.User;
import com.fpbinar6.code.models.dto.UserResponseDTO;
import com.fpbinar6.code.models.dto.UserUpdateDTO;

import java.util.List;


public interface UserService {

    public List<User> getAllUser();
    public User getUserById(int id);
    public User saveUser(User user);
    public UserResponseDTO updateUser(UserUpdateDTO userUpdateDTO);
    public void deleteUserById(int id);
}
