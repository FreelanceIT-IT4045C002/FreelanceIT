//package com.freelanceit.freelanceit.dto;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//public class Task {
//    // Getters and setters
//    private String title;
//    private String description;
//    private String assigned;
//
//}
package com.freelanceit.freelanceit.dto;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a Task in the system.
 *
 * This class maps to a database table and contains information
 * about a task, including its unique identifier, associated project,
 * title, description, assigned user, and optional screenshot path.
 */
@Entity
public @Data class Task {

    /**
     * The unique identifier for the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TaskId;

    /**
     * The project associated with the task.
     * This establishes a many-to-one relationship with the Project entity.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The title of the task.
     * This field is required and cannot be null.
     */
    @Column(nullable = false)
    private String Title;

    /**
     * A detailed description of the task.
     */
    private String Description;

    /**
     * The username of the person to whom the task is assigned.
     */
    private String assigned;

    /**
     * The file path of an optional screenshot related to the task.
     */
    private String screenshotPath;
}
