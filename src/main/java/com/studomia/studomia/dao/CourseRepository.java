package com.studomia.studomia.dao;

import com.studomia.studomia.dao.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
