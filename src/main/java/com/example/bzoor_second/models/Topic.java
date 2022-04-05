package com.example.bzoor_second.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Topic")
@Getter
@Setter
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String topicId;
    @NotBlank(message = "Topic is mandatory")
    private String topicName;
    @NotBlank(message = "description is mandatory")
    private String description;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "topicId")
    private List<Course> courses = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    public Topic(){

    }
}
