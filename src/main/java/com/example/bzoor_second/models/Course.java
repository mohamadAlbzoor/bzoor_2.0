package com.example.bzoor_second.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Course")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;
    @NotBlank(message = "course name is mandatory")
    String courseName;
    @NotBlank(message = "course description is mandatory")
    String description;

//    public String getId() {
//        return id;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public String getDescription() {
//        return description;
//    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId")
    Topic topic;
}