package com.freelanceit.freelanceit.dto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a Project entity in the system.
 * This class is mapped to a database table using JPA annotations.
 * It uses Lombok's @Data annotation to automatically generate boilerplate code.
 */
@Entity
@Data
public class Project {

    /**
     * The unique identifier for the project.
     * This field is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;

    /**
     * The user associated with this project.
     * This establishes a Many-to-One relationship with the User entity.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The title of the project.
     * This field cannot be null.
     */
    @Column(nullable = false)
    private String Title;

    /**
     * The start date of the project.
     */
    private LocalDateTime StartDate;

    /**
     * The deadline for the project completion.
     */
    private LocalDateTime Deadline;

    /**
     * The actual end date of the project.
     */
    private LocalDateTime EndDate;
}

