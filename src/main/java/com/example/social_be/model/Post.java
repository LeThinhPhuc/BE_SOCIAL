package com.example.social_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="Post")
public class Post {
    @Id
    private String id_post;

//    @ManyToOne
//    @JoinColumn(name="userid", nullable = false, referencedColumnName = "id")
//    @JsonBackReference
//    private User User;

    @ManyToOne
    @JoinColumn(name="userid", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private User user;

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
    private int like;


    @Column(name="trongso")
    private int trongso;

//    //! Cần tạo bảng mới
//    @Column(name = "liked_users")
//    private String likedUsers;

    @Column(name="tontai")
    private int tontai;


    @PrePersist
    public void prePersist() {
        this.trongso = 50;
        this.tontai = 1;
    }

    public Post() {
    }

    public Post(String id_post, User user, String noidung, String image, String privacy, int like, int trongso, int tontai) {
        this.id_post = id_post;
        this.user = user;
        this.noidung = noidung;
        this.image = image;
        this.privacy = privacy;
        this.like = like;
        this.trongso = trongso;
        this.tontai = tontai;
    }

    public void update(Post post) {
        this.setNoidung(post.getNoidung());
        this.setImage(post.getImage());
        this.setPrivacy(post.getPrivacy());
        this.setLike(post.getLike());
        this.setTrongso(post.getTrongso());
        this.setTontai(post.getTontai());
    }
}
