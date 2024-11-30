package com.freelanceit.freelanceit.dto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
public @Data class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ProjectId;
    private int UserId;
    private String Title;
    private LocalDateTime StartDate;
    private LocalDateTime Deadline;
    private LocalDateTime EndDate;
}
