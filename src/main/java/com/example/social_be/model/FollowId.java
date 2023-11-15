package com.example.social_be.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Data
@Embeddable
public class FollowId implements Serializable  {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "followed_id", nullable = false, referencedColumnName = "id")
    private User followedId;

    public FollowId(User userId, User followedId) {
        this.userId = userId;
        this.followedId = followedId;
    }

    public FollowId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowId followId)) return false;

        if (!getUserId().equals(followId.getUserId())) return false;
        return getFollowedId().equals(followId.getFollowedId());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getFollowedId().hashCode();
        return result;
    }
}