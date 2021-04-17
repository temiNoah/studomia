package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
