package com.example.restblog.services;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import com.example.restblog.data.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class UserService {

//  private   List<User> userList = setUserList();
//  private   List<Post> posts = setPostList();
    private final UsersRepository usersRepository;
  private final PostsRepository postsRepository;

    public UserService(UsersRepository userRepository, PostsRepository postsRepository ){
        this.usersRepository = userRepository;
        this.postsRepository = postsRepository;
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    public List<Post>getPostList(){
        return postsRepository.findAll();
    }

    public void addPost(Post newPost, String username){

        // get the User object who made the post
        User user = getByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        // add the post to the post list (our pretend database)
        postsRepository.save(newPost);
    }

    public User getByUserId(long id){
       return usersRepository.findById(id).orElseThrow();
    }

    public User getByUsername(String username){
      return usersRepository.findByUsername(username);
    }

    public void  deletePostById(long id){
        postsRepository.deleteById(id);
    }

//    private List<User> setUserList(){
//       List<User> userList = new ArrayList<>();
//            userList.add(new User(1, "testUser", "user@gmail.com", "password"));
//            userList.add(new User(2, "Duck", "duckman@gmail.com", "DuckMan"));
//        return userList;
//    }
//
//    private List<Post> setPostList(){
//        List<Post> postList = new ArrayList<>();
//        User user = new User(1, "DUCK", "user@gmail.com", "password");
//        postList.add( new Post(1L, "Dog pics", " this is a picture of my dog", user));
//        postList.add(new Post(2L, "Dinner", "Dinner ", user));
//        return postList;
//    }
}
