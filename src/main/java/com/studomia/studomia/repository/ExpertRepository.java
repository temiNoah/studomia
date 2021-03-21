package com.studomia.studomia.repository;


import com.studomia.studomia.dto.request.Expert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpertRepository extends CrudRepository<Expert,Long> {
    List findByLastName(String lastName);

    @Query("SELECT e FROM Student e WHERE e.age= :age")
    public List findByAge(@Param("age") int age);
}
