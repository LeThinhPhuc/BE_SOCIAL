package com.example.social_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Post")
public class Post {
    @Id
    private String id_post;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private User User;


    @Column(name="noidung")
    private String noidung;

    @Column(name="image")
    private String image;


//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="create_at")
//    private Date create_at;
//
//
//    @PrePersist
//    protected void onCreate(){
//        create_at=new Date();
//    }

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name="update_at")
//    private Date update_at;
//
//
//    @PreUpdate
//    protected void onUpdate(){
//        update_at=new Date();
//    }
//

    @Column(name="privacy")
    private String privacy;

    @Column(name="[like]")
    private String like;


    @Column(name="trongso", columnDefinition = "int default 50")
    private int trongso;

    @Column(name = "liked_users")
    private String likedUsers;

    @Column(name="tontai", columnDefinition = "int default 1")
    private int tontai;

    public Post() {
    }

    public Post(String id_post, com.example.social_be.model.User user, String noidung, String image, String privacy, String like, int trongso, String likedUsers, int tontai) {
        this.id_post = id_post;
        User = user;
        this.noidung = noidung;
        this.image = image;
        this.privacy = privacy;
        this.like = like;
        this.trongso = trongso;
        this.likedUsers = likedUsers;
        this.tontai = tontai;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public com.example.social_be.model.User getUser() {
        return User;
    }

    public void setUser(com.example.social_be.model.User user) {
        User = user;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public int getTrongso() {
        return trongso;
    }

    public void setTrongso(int trongso) {
        this.trongso = trongso;
    }

    public String getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(String likedUsers) {
        this.likedUsers = likedUsers;
    }

    public int getTontai() {
        return tontai;
    }

    public void setTontai(int tontai) {
        this.tontai = tontai;
    }
}
