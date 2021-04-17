package com.studomia.studomia.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.studomia.studomia.dto.request.signup.Expert;
import com.studomia.studomia.dto.request.signup.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Set;

//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course
{
    //@JsonIgnore
    @ApiModelProperty(hidden = true)
    private Long courseId=0L;

    @ApiModelProperty(hidden = false)
    private String title;

    @ApiModelProperty(hidden = false)
    private String description;

    @ApiModelProperty(hidden = false)
    private String duration;

   // public Course(long courseId,String title,String description,String duration){}

   // public Course() {}

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
}
