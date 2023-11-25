package com.example.social_be.repository;

import com.example.social_be.model.Follow;
import com.example.social_be.model.FollowId;
import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
////
public interface FollowRepository extends JpaRepository<Follow, FollowId>{
//    @Query("SELECT f FROM Follow f WHERE f.id.userId = :userId")


    List<Follow> findFollowByUsers(@Param("users") String users);

    List<Follow> findFollowsByFollowed(@Param("followed") String followed);

    Optional<Follow> findFollowByUsersAndFollowed(@Param("users") String users, @Param("followed") String followed);

}
