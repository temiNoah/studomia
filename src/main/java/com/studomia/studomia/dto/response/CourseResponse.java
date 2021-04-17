package com.studomia.studomia.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.studomia.studomia.dao.entities.Student;
import com.studomia.studomia.dto.request.signup.Expert;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseResponse extends Response{

    private Long courseId=0L;

    private String title;

    private String description;

    private String duration;

    Set<Student> enrolledStudents = new HashSet<>();

    private Expert expert;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    @Override
    public String toString(){
        return String.format("Course Response[ course Id=%d title='%s' , description='%s' , duration='%s',expert='%s',enrolled student='%s']",
                this.courseId,this.title,this.description,this.duration,this.expert,this.enrolledStudents);
    }
}
