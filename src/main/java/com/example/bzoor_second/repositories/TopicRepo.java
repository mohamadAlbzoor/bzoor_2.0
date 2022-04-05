package com.example.bzoor_second.repositories;

import com.example.bzoor_second.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, String> {
    Topic findByTopicId(String topicId);
}
