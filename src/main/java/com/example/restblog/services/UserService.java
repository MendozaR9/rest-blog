package com.example.restblog.services;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class UserService {
  private   List<User> userList = setUserList();
  private   List<Post> posts = setPostList();

    public List<User> getUserList(){
        return userList;
    }

    public List<Post>getPostList(){
        return posts;
    }

    public  void  addPost(Post newPost, String username){

        User user = getByUsername(username);

        user.getPosts().add(newPost);

        newPost.setUser(user);

        posts.add(newPost);
    }

    public User getByUserId(long id){
        for (User user: userList) {
            if (Objects.equals(user.getId(), id)){
                return user;
            }
        }
        return null;
    }

    public User getByUsername(String username){
        for (User user: userList) {
            if (Objects.equals(user.getUsername(), username)){
                return user;
            }
        }
        return null;
    }

    private List<User> setUserList(){
       List<User> userList = new ArrayList<>();
            userList.add(new User(1, "testUser", "user@gmail.com", "password"));
            userList.add(new User(2, "Duck", "duckman@gmail.com", "DuckMan"));
        return userList;
    }

    private List<Post> setPostList(){
        List<Post> postList = new ArrayList<>();
        postList.add( new Post(1L, "Dog pics", " this is a picture of my dog"));
        postList.add(new Post(2L, "Dinner", "Dinner "));
        return postList;
    }
}
