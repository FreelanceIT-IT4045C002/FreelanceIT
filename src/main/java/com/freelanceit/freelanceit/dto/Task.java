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
 * Represents a Task entity in the system.
 * This class is mapped to a database table using JPA annotations.
 * It uses Lombok's @Data annotation to automatically generate boilerplate code.
 */
@Entity
@Data
public class Task {

    /**
     * The unique identifier for the task.
     * This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TaskId;

    /**
     * The project associated with this task.
     * This establishes a Many-to-One relationship with the Project entity.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The title of the task.
     * This field cannot be null.
     */
    @Column(nullable = false)
    private String Title;

    /**
     * A detailed description of the task.
     */
    private String Description;

    /**
     * The person or entity assigned to this task.
     */
    private String assigned;

    /**
     * The file path to a screenshot associated with this task, if any.
     */
    private String screenshotPath;
}
