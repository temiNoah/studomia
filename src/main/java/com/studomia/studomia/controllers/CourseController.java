package com.studomia.studomia.controllers;

import com.studomia.studomia.dto.request.Course;
import com.studomia.studomia.dto.response.CourseResponse;
import com.studomia.studomia.dto.response.Response;
import com.studomia.studomia.services.CourseServices;
import com.studomia.studomia.services.ExpertServices;
import com.studomia.studomia.services.StudentServices;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {


    private CourseServices courseService ;
    private StudentServices studentService;
    private ExpertServices expertService;

    @Autowired
    CourseController(ExpertServices e , StudentServices s,CourseServices c )
    {
        courseService = c;
        studentService =s;
        expertService = e;
    }

    @GetMapping("/all")
    public List<CourseResponse> getCourses() throws IOException
    {
        return courseService.getCourses();
    }

    @PostMapping("/add")
    public CourseResponse addCourse(@RequestBody Course course) throws IOException
    {
        return courseService.addCourse(course);
    }

    @DeleteMapping("/course/{courseId}/delete")
    public Response deleteCourse(@PathVariable Long courseId) throws NotFoundException
    {
        Response response = new Response() ;
        response.setCode(HttpStatus.OK.getReasonPhrase());
        response.setMessage(courseService.deleteCourse(courseId));

        return  response;
    }

    @PutMapping("/course/{courseId}/edit")
    public CourseResponse editCourse(@RequestBody Course course, @PathVariable Long courseId) throws IOException,NotFoundException
    {
        return courseService.editCourse(course,courseId);
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourse(@PathVariable("courseId") Long courseId,
            @RequestHeader(name = "X-COM-PERSIST",required = true) String headerPersist,
            @RequestHeader(name="X-COM-LOCATION",defaultValue = "ASIA") String headerLocation
    ) throws  IOException, NotFoundException {
        return courseService.getCourse(courseId);
    }


    @PutMapping("/course/{courseId}/student/{studentId}")
    public CourseResponse addStudentToCourse(@PathVariable("courseId") Long courseId, @PathVariable("studentId") Long studentId) throws NotFoundException, IOException
    {
        return  courseService.addCourseToStudent(courseId,studentId);// courseService.editCourse(course);
    }

    @PutMapping("/{courseId}/expert/{expertId}")
    public CourseResponse assignExpertToCourse(@PathVariable("courseId") Long courseId, @PathVariable("expertId") Long expertId) throws NotFoundException, IOException {
      return  courseService.assignCourseToExpert(courseId,expertId);
    }

}
