package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.SSOuser;
import com.studomia.studomia.dao.entities.User;
import com.studomia.studomia.dao.entities.UserDeprecated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SSOuserRepository extends JpaRepository<SSOuser, Long>
 {

    Optional<SSOuser> findByEmail(String email);

    Boolean existsByEmail(String email);

//    Optional<SSOuser> findByUsername(String username);
//
//    Boolean existsByUsername(String username);
//
}
