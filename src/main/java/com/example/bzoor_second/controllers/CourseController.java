package com.example.bzoor_second.controllers;

import com.example.bzoor_second.models.Course;
import com.example.bzoor_second.models.Topic;
import com.example.bzoor_second.models.User;
import com.example.bzoor_second.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    CourseService courseService;


    @GetMapping("/courses")
    public List<Course> getALlCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/Courses/{id}")
    public Course getCourses(@PathVariable String id){
        return this.courseService.getCourse(id);
    }

    @PostMapping("/courses")
    public void addCourse(@RequestParam String topicId, @RequestBody Course course){
        System.out.println(topicId+" --> the value of the topic id");
        course.setTopic(new Topic(topicId, "", "", new ArrayList<>(),new User()));
        this.courseService.addCourse(course);
    }

    //this thing is just form me ~
    @PostMapping("/v2/courses")
    public void addCourse(@RequestBody Course course){
        this.courseService.addCourse(course);
    }

    @DeleteMapping("/topics/Courses/{courseId}")
    public void deleteCourse(@PathVariable String courseId){
        this.courseService.deleteCourse(courseId);
    }

    @PutMapping("/topics/Courses")
    public void update(@RequestBody Course course){
        this.courseService.put(course);
    }

    @PatchMapping("/topics/Courses/{courseId}")
    public void UpdateDescription(@PathVariable String courseId, @RequestBody Map<String, Object> courseDescription){
        this.courseService.patch(courseId, courseDescription.get("description").toString());
    }
}
