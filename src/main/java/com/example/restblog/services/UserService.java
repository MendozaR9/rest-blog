package com.example.restblog.services;

import com.example.restblog.data.*;
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
  private final PostsRepository postsRepository;
  private final CategoriesRepository categoriesRepository;

    public UserService(UsersRepository userRepository, PostsRepository postsRepository, CategoriesRepository categoriesRepository ){
        this.usersRepository = userRepository;
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
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

        List<Category> categoriesToAdd = new ArrayList<>();

        for (Category category : newPost.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(category.getName()));
        }

        newPost.setCategories(categoriesToAdd);

        // add the post to the post list (our pretend database)
        postsRepository.save(newPost);
    }

    public User getByUserId(long id){
       return usersRepository.findById(id).orElseThrow();
    }

    public User getByUsername(String username){
      return usersRepository.findByUsername(username);
    }

    public void updatePost(long postId, Post post){
        Post postToUpdate = postsRepository.findById(postId).orElseThrow();

        if (post.getContent() != null  && !post.getContent().isEmpty()){
            postToUpdate.setContent(post.getContent());
        }
        if (post.getTitle() != null && !post.getTitle().isEmpty()){
            postToUpdate.setTitle(post.getTitle());
        }

        postsRepository.save(postToUpdate);
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

    public void  deletePostById(long id){
        postsRepository.deleteById(id);
    }


    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTitleLike(keyword);
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
