package com.example.social_be.service;

import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import com.example.social_be.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImplement implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addPost(Post post){
        if(post!=null){
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public Post updatePost(String id, Post post){
        if(post!=null){
            Post post1=postRepository.getById(id);
            if(post1!=null){
                post1.setTrongso(post.getTrongso());
                post1.setPrivacy(post.getPrivacy());
                post1.setNoidung(post.getNoidung());
                post1.setTontai(post.getTontai());
                post1.setLike(post.getLike());
                post1.setImage(post.getImage());
                post1.setLikedUsers(post.getLikedUsers());
            }
        }
        return null;
    }

}
