package com.studomia.studomia.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
//@AllArgsConstructor
@Entity
@Table(name="course")
public class Course //implements Serializable
{
   // private static final long serialVersionUID= -3009157732242241609L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="course_id")
    private long courseId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;


    @ManyToMany(targetEntity=com.studomia.studomia.dao.entities.Student.class,fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "course_id",nullable=false, updatable=true),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    Set<Student> enrolledStudents = new HashSet<>();

    @ManyToOne(optional=true,fetch = FetchType.EAGER)//yet get
    @JoinColumn(name = "expert_id", referencedColumnName = "expert_id")//name=where JPA keeps the foreign key/column for joining ; referencedColumnname=column name in Expert table that is primary key
    private Expert expert;

    @Column(name="duration")
    private String duration;

//    @ManyToMany()
//    private Schedule schedule;
    //public Course(){}
    //public Course(long courseId,String title,String description,String duration) {}

    public Set<Student> getEnrolledStudents()
    {
        return enrolledStudents;
    }

    public void setExpert(Expert expert)
    {
        this.expert = expert;
    }


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
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

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Expert getExpert() {
        return expert;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString(){
        return String.format("Course[ title='%s' , description='%s' ,duration='%s' ,expert='%s']",title,description,duration,expert);
    }
}
