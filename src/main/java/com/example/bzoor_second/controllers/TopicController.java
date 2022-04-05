package com.example.bzoor_second.controllers;

import com.example.bzoor_second.models.Course;
import com.example.bzoor_second.models.Topic;
import com.example.bzoor_second.models.User;
import com.example.bzoor_second.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping("/topics")
    public List<Topic> getALlTopics(){
        return topicService.getAllTopics();
    }

    @GetMapping("/topics/{topicId}")
    public List<Course> getCourses(@PathVariable String topicId){
        return topicService.getCoursesForTopic(topicId);
    }

    @PostMapping("/topics")
    public void addTopic(@RequestParam int userId, @RequestBody Topic topic){
        System.out.println(userId+" --> the value of the topic id");
        topic.setUser(new User(userId,"", "",true,"", new ArrayList<Topic>()));
        this.topicService.addTopic(topic);
    }

    @DeleteMapping("/topics/{topicId}")
    public void deleteTopicCorrectly(@PathVariable String topicId){
        System.out.println("first");
        this.topicService.delete(topicId);
    }
}