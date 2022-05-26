package com.example.restblog.services;

import com.example.restblog.data.*;
import com.example.restblog.web.dto.CreatePostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;
    public final UserService userService;

    public PostService(PostsRepository postsRepository,
                       CategoriesRepository categoriesRepository,
                       UserService userService) {
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
        this.userService = userService;
    }


    public List<Post> getPostList(){
        return postsRepository.findAll();
    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTitleLike(keyword);
    }


    public void addPost(Post newPost, String username, CreatePostDto dto){

        // get the User object who made the post
        User user = userService.getByUsername(username);

        newPost.setTitle(dto.getTitle());
        newPost.setContent(dto.getContent());

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (String category : dto.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(category));
        }

        newPost.setCategories(categoriesToAdd);

        // add the post to the post list (our pretend database)
        postsRepository.save(newPost);
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


    public void  deletePostById(long id){
        postsRepository.deleteById(id);
    }

}
