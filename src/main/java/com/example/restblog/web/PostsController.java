package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final UserService userServices;

    public PostsController(UserService userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<Post> getAll() {
        return userServices.getPostList();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id) {

        for (Post post : userServices.getPostList()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return null;
    }

    @PostMapping
    public void createPost(@RequestBody Post newPost) {
        System.out.println(newPost);
    }

    @PutMapping("{id}")
    public void updatePost(@RequestBody Post post, @PathVariable long id) {
        for (Post oldPost : userServices.getPostList()) {
            if (Objects.equals(oldPost.getId(), id)) {
                System.out.println(oldPost);
                post.setId(id);
                oldPost.setContent(post.getContent());
                oldPost.setTitle(post.getTitle());
                System.out.println(post);
            }
        }
    }

    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody Post newPost) {
        userServices.addPost(newPost, username);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable long id) {
        userServices.deletePostById(id);
    }
}
