package com.coloc.crud.coloc.services;

import com.coloc.crud.coloc.models.User;

public interface UserService {
    User getUserByUsername(String username);
    User saveOrUpdateUser(User user);

}
