package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

     @Query(value="SELECT * FROM Student e WHERE e.username= :username",nativeQuery = true)
     public Optional<Student> findByUsername(@Param("username") String username);

    @Query(value="SELECT * FROM Student e WHERE e.email=:email",nativeQuery = true)
    Optional<Student> existsByEmail(@Param("email") String username);
//    @Value("${query}")
//     String query="";
//
//    List findByLastName(String lastName);
//
//    @Query("SELECT e FROM Student e WHERE e.age= :age")
//    public List findByAge(@Param("age") int age);

    //void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);

//    @Modifying(clearAutomatically = true) //clears the repository object
//    @Query("update Student s set s.fist_name =: where s.id =:entryId") //@Query(query)
//    Student updateById(@Param("")Student student);

}
