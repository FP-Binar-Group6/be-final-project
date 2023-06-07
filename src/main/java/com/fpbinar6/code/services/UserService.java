package com.fpbinar6.code.services;

import com.fpbinar6.code.models.User;

import java.util.List;


public interface UserService {

    public List<User> getAllUser();
    public User getUserById(int id);
    public User saveUser(User user);
    public User updateUser(User user);
    public void deleteUserById(int id);
}
