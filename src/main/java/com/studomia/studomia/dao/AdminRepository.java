package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.Admin;
import com.studomia.studomia.dao.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    @Query(value="SELECT * FROM Admin e WHERE e.username= :username",nativeQuery = true)
    public Optional<Admin> findByUsername(@Param("username") String username);

    @Query(value="SELECT * FROM Admin e WHERE e.email= :email",nativeQuery = true)
    Optional<Admin> existsByEmail(@Param("email") String username);
}
