package com.example.social_be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "nguoidung")
public class User {
    @Id
    @Column(name = "id")
    private String id;

//    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Post> Post;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> Post;

    @Column(name = "email", columnDefinition = "nvarchar")
    private String email;

    @Column(name = "username", columnDefinition = "nvarchar")
    private String username;

    @Column(name = "password", columnDefinition = "nvarchar")
    private String password;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="create_at")
//    private Date create_at;
//
//    @PrePersist
//    protected void onCreate(){
//        create_at=new Date();
//    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="update_at")
//    private Date update_at;
//
//    @PreUpdate
//    protected void onUpdate(){
//        update_at=new Date();
//    }
    @Column(name = "number_following")
    private int number_following;

    @Column(name = "number_post")
    private int number_post;

    @Column(name = "number_followed")
    private int number_followed;

    @Column(name="avatar", columnDefinition = "nvarchar")
    private String avatar;

    public User(String username) {
        this.username = username;
    }
    public User() {
    }

    public User(User user) {
        this(user.getId(), user.getPost(), user.getEmail(), user.getUsername(),
                user.getPassword(), user.getNumber_following(), user.getNumber_post(), user.getNumber_followed(), user.getAvatar());
    }

    public User(String id, String email, String username, String password, int number_following, int number_post, int number_followed, String avatar) {
        this(id, new ArrayList<>(), email, username, password, number_following, number_post, number_followed, avatar);
    }

    public User(String id, List<com.example.social_be.model.Post> post, String email, String username, String password, int number_following, int number_post, int number_followed, String avatar) {
        this.id = id;
        Post = post;
        this.email = email;
        this.username = username;
        this.password = password;
        this.number_following = number_following;
        this.number_post = number_post;
        this.number_followed = number_followed;
        this.avatar = avatar;
    }



//    public Date getCreate_at() {
//        return create_at;
//    }
//
//    public void setCreate_at(Date create_at) {
//        this.create_at = create_at;
//    }
//
//    public Date getUpdate_at() {
//        return update_at;
//    }
//
//    public void setUpdate_at(Date update_at) {
//        this.update_at = update_at;
//    }



}
