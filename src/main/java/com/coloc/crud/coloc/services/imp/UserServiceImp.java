package com.coloc.crud.coloc.services.imp;

import com.coloc.crud.coloc.models.User;
import com.coloc.crud.coloc.repositories.UserRepository;
import com.coloc.crud.coloc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveOrUpdateUser(User user) {
        userRepository.save(user);
        return user;
    }
}
