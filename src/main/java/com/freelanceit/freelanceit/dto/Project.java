package com.freelanceit.freelanceit.dto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity class representing a Project in the system.
 *
 * This class maps to a database table and contains information
 * about a project, including its unique identifier, associated user,
 * title, and date-related attributes such as start date, deadline,
 * and end date.
 */
@Entity
public @Data class Project {

    /**
     * The unique identifier for the project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;

    /**
     * The user associated with the project.
     * This establishes a many-to-one relationship with the User entity.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The title of the project.
     * This field is required and cannot be null.
     */
    @Column(nullable = false)
    private String Title;

    /**
     * The start date and time of the project.
     */
    private LocalDateTime StartDate;

    /**
     * The deadline for the project.
     */
    private LocalDateTime Deadline;

    /**
     * The end date and time of the project.
     */
    private LocalDateTime EndDate;
}

