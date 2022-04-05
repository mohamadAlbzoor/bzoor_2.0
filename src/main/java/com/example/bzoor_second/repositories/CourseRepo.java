package com.example.bzoor_second.repositories;

import com.example.bzoor_second.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, String> {
    public List<Course> findByTopicTopicId(String topicId);
}