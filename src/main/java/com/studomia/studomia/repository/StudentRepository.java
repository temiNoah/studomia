package com.studomia.studomia.repository;

import com.studomia.studomia.dto.request.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Value("${query}")
     String query="";

    List findByLastName(String lastName);

    @Query("SELECT e FROM Student e WHERE e.age= :age")
    public List findByAge(@Param("age") int age);

    //void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);

//    @Modifying(clearAutomatically = true) //clears the repository object
//    @Query("update Student s set s.fist_name =: where s.id =:entryId") //@Query(query)
//    Student updateById(@Param("")Student student);

}
