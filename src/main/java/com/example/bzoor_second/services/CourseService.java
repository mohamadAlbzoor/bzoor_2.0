package com.example.bzoor_second.services;

import com.example.bzoor_second.handler.NotAuthorized;
import com.example.bzoor_second.models.Course;
import com.example.bzoor_second.models.Topic;
import com.example.bzoor_second.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    private boolean authorized(String id){
        Topic topic = this.courseRepository.findById(id).get().getTopic();
        System.out.println(myUserDetailsService.getUser().userId()+" <---- this is the current logged in user id");
        System.out.println(topic.getUser().userId()+" <---- this is the course owner id");

        return myUserDetailsService.getUser().userId() == topic.getUser().userId();
    }

    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        this.courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public Course getCourse(String id){
        return this.courseRepository.findById(id).get();
    }

    public void addCourse(Course course){
        this.courseRepository.save(course);
    }

    public void deleteCourse(String id){
        if(authorized(id)) {
            this.courseRepository.deleteById(id);
        }else {
            throw new NotAuthorized("this user can not delete this course");
        }
    }

    public void put(Course course){
        if(authorized(course.getId())) {
            this.courseRepository.save(course);
        }else {
            throw new NotAuthorized("this user can not put to this course");
        }
    }

    public void patch(String courseId, String des){
        if(authorized(courseId)) {
            Course course = this.courseRepository.findById(courseId).get();
            course.setDescription(des);
            this.courseRepository.save(course);
        }else {
            throw new NotAuthorized("this user can not patch to this course");
        }
    }

}