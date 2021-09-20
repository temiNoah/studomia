package com.studomia.studomia.dao;


import com.studomia.studomia.dao.entities.Expert;
import com.studomia.studomia.dao.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {

    @Query(value="SELECT * FROM Expert e WHERE e.username= :username",nativeQuery = true)
    public Optional<Expert> findByUsername(@Param("username") String username);



    @Query(value="SELECT * FROM Expert e WHERE e.email= :email",nativeQuery = true)
    Optional<Expert> existsByEmail(@Param("email") String username);
}
