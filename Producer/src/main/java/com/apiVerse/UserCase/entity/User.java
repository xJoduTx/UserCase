package com.apiVerse.UserCase.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@ToString
@AllArgsConstructor
@Table(name = "users")
public class User {
    public User() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String phoneNumber;
}
