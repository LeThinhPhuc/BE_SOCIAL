
package com.example.social_be.service;

import com.example.social_be.model.Post;
import com.example.social_be.model.User;
import com.example.social_be.repository.PostRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplement implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public Post updatePost(String id, Post post) {
        if (post == null) return null;

        try {
            Post postReturned = postRepository.getById(id);

            //! Cần tối ưu
            postReturned.setNoidung(post.getNoidung() == null ? postReturned.getNoidung() : post.getNoidung());
            postReturned.setImage(post.getImage() == null ? postReturned.getImage() : post.getImage());
            postReturned.setPrivacy(post.getPrivacy() == null ? postReturned.getPrivacy() : post.getPrivacy());
            postReturned.setLike(post.getLike() == 0 ? postReturned.getLike() : post.getLike());
            postReturned.setTrongso(post.getTrongso() == 0 ? postReturned.getTrongso() : post.getTrongso());

            return postRepository.save(postReturned);
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Post addPost(Post post) {
        if (post == null) return null;

        System.out.println("Đang ở [PostServiceImplement]");
        return postRepository.save(post);
    }

//    @Override
//    public Post updatePost(String id, Post post) {
//        if (post == null) return null;
//
//        try {
//            Post postReturned = postRepository.findPostById(id);
//
//
//            postReturned.update(post);
//            return postRepository.save(postReturned);
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @Override
    public List<Post> listPost(String userId) {
        try {
            return postRepository.findByUserId(userId);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deletePost(String postId) {
        try {
            Post postReturned = postRepository.getById(postId);
            postRepository.delete(postReturned);

            return true;
        }catch (Exception e) {
            return  false;
        }
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post getPost(String postId) {
        try {
            return postRepository.findById(postId).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
