package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
