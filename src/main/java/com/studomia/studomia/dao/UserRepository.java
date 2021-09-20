package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.SSOuser;
import com.studomia.studomia.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository{
        //extends JpaRepository<User,Long>

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
