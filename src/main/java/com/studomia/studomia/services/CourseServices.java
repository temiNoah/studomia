package com.studomia.studomia.services;

import com.studomia.studomia.dto.request.Course;
import com.studomia.studomia.dto.response.CourseResponse;
import com.studomia.studomia.exceptions.NotFoundException;


import java.io.IOException;
import java.util.List;

public interface CourseServices {

    public List<CourseResponse> getCourses() throws IOException;
    public CourseResponse addCourse(Course course) throws IOException;
    public String deleteCourse( Long id) throws NotFoundException;
    public CourseResponse editCourse(  Course course,Long courseId) throws IOException, NotFoundException;
    public CourseResponse getCourse(Long courseId) throws IOException , NotFoundException;
    public CourseResponse assignCourseToExpert(Long expertId , Long courseId) throws NotFoundException, IOException;

    CourseResponse addCourseToStudent(Long courseId, Long studentId) throws IOException, NotFoundException;
}
