package com.taskhub.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(name="project_name")
    private String projectName;

    @Column(name= "project_description")
    private String projectDescription;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @Column(name= "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "project_status")
    private String projectStatus;

    @Column(name= "created_at")
    private LocalDate createdAt;


}
