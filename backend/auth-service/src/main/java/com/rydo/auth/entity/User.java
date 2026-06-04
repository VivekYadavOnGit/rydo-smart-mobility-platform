package com.rydo.auth.entity;

import com.rydo.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String fullName;

    @Column(unique = true)
    String phoneNumber;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    Boolean verified = false;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @PrePersist
    public void prepersist(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preupdate() {
        updatedAt = LocalDateTime.now();
    }
}
