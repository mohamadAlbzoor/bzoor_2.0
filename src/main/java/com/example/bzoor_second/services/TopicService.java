package com.example.bzoor_second.services;

import com.example.bzoor_second.handler.NotAuthorized;
import com.example.bzoor_second.models.Course;
import com.example.bzoor_second.models.Topic;
import com.example.bzoor_second.repositories.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepo topicRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    public List<Topic> getAllTopics(){
        List<Topic> topics = new ArrayList<>();
        this.topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public List<Course> getCoursesForTopic(String id){
        Topic topic = this.topicRepository.findByTopicId(id);
        return topic.getCourses();
    }

    public void addTopic(Topic topic){
        this.topicRepository.save(topic);
    }

    public void delete(String id){
        Topic topic = this.topicRepository.findByTopicId(id);
        System.out.println(myUserDetailsService.getUser().userId()+" <---- this is the current logged in user id");
        System.out.println(topic.getUser().userId()+" <---- this is the topic owner id");
        if(myUserDetailsService.getUser().userId() == topic.getUser().userId()){
            this.topicRepository.deleteById(id);
        }else {
            throw new NotAuthorized("this user can not delete this topic");
        }
    }

}