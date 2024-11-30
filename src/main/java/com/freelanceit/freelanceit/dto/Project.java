package com.freelanceit.freelanceit.dto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
public @Data class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String Title;
    private LocalDateTime StartDate;
    private LocalDateTime Deadline;
    private LocalDateTime EndDate;
}

