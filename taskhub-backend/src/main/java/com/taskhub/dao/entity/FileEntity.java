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
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private int file_id;

    @Column(name = "file_name")
    private String file_name;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "uploaded_at")
    private LocalDate uploadedAt;
}
