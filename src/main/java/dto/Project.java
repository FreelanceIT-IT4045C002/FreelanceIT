package dto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
public @Data class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ProjectId;
    private int UserId;
    private String Name;
    private OffsetDateTime StartDate;
    private OffsetDateTime Deadline;
    private OffsetDateTime EndDate;
}
