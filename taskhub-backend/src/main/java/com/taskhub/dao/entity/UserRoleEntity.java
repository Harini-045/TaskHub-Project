package com.taskhub.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userrole_id")
    private int userroleId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @ManyToOne()
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}
