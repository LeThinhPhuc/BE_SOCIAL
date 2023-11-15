package com.example.social_be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
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

    public User(String username) {
        this.username = username;
    }
    public User() {
    }

    public User(User user) {
        this(user.getId(), user.getPost(), user.getEmail(), user.getUsername(),
                user.getPassword(), user.getNumber_following(), user.getNumber_post(), user.getNumber_followed());
    }

    public User(String id, String email, String username, String password, int number_following, int number_post, int number_followed) {
        this(id, new ArrayList<>(), email, username, password, number_following, number_post, number_followed);
    }

    public User(String id, List<com.example.social_be.model.Post> post, String email, String username, String password,
                int number_following, int number_post, int number_followed) {
        this.id = id;
        Post = post;
        this.email = email;
        this.username = username;
        this.password = password;
        //  this.create_at = create_at;
        // this.update_at = update_at;
        this.number_following = number_following;
        this.number_post = number_post;
        this.number_followed = number_followed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<com.example.social_be.model.Post> getPost() {
        return Post;
    }

    public void setPost(List<com.example.social_be.model.Post> post) {
        Post = post;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getNumber_following() {
        return number_following;
    }

    public void setNumber_following(int number_following) {
        this.number_following = number_following;
    }

    public int getNumber_post() {
        return number_post;
    }

    public void setNumber_post(int number_post) {
        this.number_post = number_post;
    }

    public int getNumber_followed() {
        return number_followed;
    }

    public void setNumber_followed(int number_followed) {
        this.number_followed = number_followed;
    }
}
