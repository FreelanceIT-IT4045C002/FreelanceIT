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

@Entity
public @Data class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int TaskId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String Title;
    private String Description;
    private String assigned;
    private String screenshotPath;

}
