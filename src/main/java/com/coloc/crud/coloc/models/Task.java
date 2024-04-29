package com.coloc.crud.coloc.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long idTask;
    private String descriptionT;
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User assignedTo;
    @ManyToOne
    @JoinColumn(name="flatShare_id")
    private FlatShare flatShareTasks;

    @JsonIgnore
    public User getAssignedTo() {
        return assignedTo;
    }

    @JsonIgnore
    public FlatShare getFlatShareTasks() {
        return flatShareTasks;
    }
}
