package com.example.social_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "follow")
public class Follow {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false, referencedColumnName = "id")
    private User followed;
}
