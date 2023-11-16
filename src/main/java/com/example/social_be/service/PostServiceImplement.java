package com.example.social_be.service;

import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import com.example.social_be.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                post1.setTrongso(post.getTrongso()==0? post1.getTrongso() : post.getTrongso());
                post1.setPrivacy(post.getPrivacy()==null? post1.getPrivacy() : post.getPrivacy());
                post1.setNoidung(post.getNoidung()==null? post1.getNoidung() : post.getNoidung());
//                post1.setTontai(post.getTontai());
                post1.setLike(post.getLike()==null? post1.getLike() : post.getLike());
                post1.setImage(post.getImage()==null? post1.getImage() : post.getImage());
                post1.setLikedUsers(post.getLikedUsers()==null? post1.getLikedUsers() : post.getLikedUsers());
                return postRepository.save(post1);
            }
        }
        return null;
    }

    @Override
    public List<Post> listPost(String userId){

        return postRepository.findByUserId(userId);
    }

    @Override
    public Post deletePost(String postId){
        if(postId!=null){
            Post tmp=postRepository.getById(postId);
            tmp.setTontai(0);
            return postRepository.save(tmp);
        }
        return null;
    }

    @Override
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

}
