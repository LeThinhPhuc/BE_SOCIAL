package com.example.social_be.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@Data
@Builder
@Entity
@Table(name = "follow")
public class Follow {
    @Id
//    @Column(name = "id")
    private FollowId id;

    @Column(name = "users")
    private String users;

    @Column(name = "followed")
    private String followed;

    public Follow(FollowId id) {
        this(id.getUserId(), id.getFollowedId());
    }
    public Follow(User userId, User followedId) {
        this(new FollowId(userId, followedId), userId.getId(), followedId.getId());
    }
    public Follow() {

    }

    public Follow(FollowId id, String users, String followed) {
        this.id = id;
        this.users = users;
        this.followed = followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Follow follow)) return false;

        return getId().equals(follow.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
