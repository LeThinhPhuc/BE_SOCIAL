package com.example.social_be.service;


import com.example.social_be.model.User;
import com.example.social_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;




    @Override
    public User addUser(User user){
        if(user!=null){
//            return userRepository.save(user);
            User checkEmail=userRepository.findByEmail(user.getEmail());
            if(checkEmail!=null){
                return null;
            }else{
                return userRepository.save(user);
            }
        }
        return null;
    }

    @Override
    public User updateUser(String id, User user){
        if(user!=null){
            User user1=userRepository.getById(id);
            if(user1!=null){
                user1.setEmail(user.getEmail()==null? user1.getEmail() : user.getEmail());
                user1.setUsername(user.getUsername()==null? user1.getUsername() : user.getUsername());
                user1.setPassword(user.getPassword()==null? user1.getPassword() : user.getPassword());
             //   user1.setCreate_at(user.getCreate_at());
           //     user1.setUpdate_at(user.getUpdate_at());
                user1.setNumber_following(user.getNumber_following());
                user1.setNumber_post(user.getNumber_post()==0? user1.getNumber_post() : user.getNumber_post());
                user1.setNumber_followed(user.getNumber_followed());
                return userRepository.save(user1);
            }
        }
        return null;
    }

    @Override
    public List<User> deleteUser(String id){
        if(id!=null){
           User user=userRepository.getById(id);
           if(user!=null){
               userRepository.delete(user);
               return userRepository.findAll();
           }
        }
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(String id){
//        User tmp=new User();
//        tmp.setId(userRepository.getById(id).getId());
//        tmp.setEmail(userRepository.getById(id).getEmail());
//        tmp.setPassword(userRepository.getById(id).getPassword());
//      //  tmp.setCreate_at(userRepository.getById(id).getCreate_at());
//      //  tmp.setUpdate_at(userRepository.getById(id).getUpdate_at());
//        tmp.setNumber_following(userRepository.getById(id).getNumber_following());
//        tmp.setNumber_post(userRepository.getById(id).getNumber_post());
//        tmp.setNumber_followed(userRepository.getById(id).getNumber_followed());
//
//
//
//
//        return tmp;
        return new User(userRepository.getById(id));
    }

    @Override
    public User checkAuth(User user){
        User userOptional=userRepository.findByEmail(user.getEmail());
        if(userOptional!=null){
               if(Objects.equals(user.getPassword(),userOptional.getPassword())){
                   return userOptional;
               }
        }
        return null;
    }
}
