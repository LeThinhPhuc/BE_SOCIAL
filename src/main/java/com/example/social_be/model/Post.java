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

    public Post() {
    }

    public Post(String id_post, com.example.social_be.model.User user, String noidung, String image, String privacy, String like) {
        this.id_post = id_post;
        User = user;
        this.noidung = noidung;
        this.image = image;
       // this.create_at = create_at;
        //this.update_at = update_at;
        this.privacy = privacy;
        this.like = like;
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

//    public Date getCreate_at() {
//        return create_at;
//    }
//
//    public void setCreate_at(Date create_at) {
//        this.create_at = create_at;
//    }

//    public Date getUpdate_at() {
//        return update_at;
//    }
//
//    public void setUpdate_at(Date update_at) {
//        this.update_at = update_at;
//    }

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
}
