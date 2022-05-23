package com.example.restblog.services;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
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

    public void addPost(Post newPost, String username){

        // get the User object who made the post
        User user = getByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        // add the post to the post list (our pretend database)
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

    public void  deletePostById(long id){
        for (Post post: posts){
            if (post.getId() == id ){
                posts.remove(post);
                return;
            }
        }
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
