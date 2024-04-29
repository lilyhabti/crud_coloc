package com.coloc.crud.coloc.repositories;

import com.coloc.crud.coloc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
