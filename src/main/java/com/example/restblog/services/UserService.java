package com.example.restblog.services;

import com.example.restblog.data.*;
import com.example.restblog.web.dto.CreatePostDto;
import com.example.restblog.web.dto.UpdateUserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class UserService {

//  private   List<User> userList = setUserList();
//  private   List<Post> posts = setPostList();
    private final UsersRepository usersRepository;


    public UserService(UsersRepository userRepository ){
        this.usersRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }


    public User getByUserId(long id){
       return usersRepository.findById(id).orElseThrow();
    }

    public User getByUsername(String username){
      return usersRepository.findByUsername(username);
    }

    public void updateUser(UpdateUserDto updateUserDto){
        User user = usersRepository.findById(updateUserDto.getId()).orElseThrow();
        if (updateUserDto.getUsername() != null && !updateUserDto.getUsername().isEmpty()){
            user.setUsername(updateUserDto.getUsername());
        }

        if (updateUserDto.getEmail() != null && !updateUserDto.getEmail().isEmpty()){
            user.setEmail(updateUserDto.getEmail());
        }

        usersRepository.save(user);
}

    public User getByEmail(String email){
        return usersRepository.findByEmail(email);
    }



}
