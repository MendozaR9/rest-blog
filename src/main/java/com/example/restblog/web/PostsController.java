package com.example.restblog.web;

import com.example.restblog.services.EmailService;
import com.example.restblog.data.Post;
import com.example.restblog.services.PostService;
import com.example.restblog.services.UserService;
import com.example.restblog.web.dto.CreatePostDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {


    private final EmailService emailService;
    private final PostService postService;


    public PostsController( EmailService emailService, PostService postService) {

        this.emailService = emailService;
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getPostList();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id) {

        for (Post post : postService.getPostList()) {
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
    public void updatePost(@RequestBody Post updatePost, @PathVariable long id) {
        postService.updatePost(id, updatePost);
//        for (Post oldPost : userServices.getPostList()) {
//            if (Objects.equals(oldPost.getId(), id)) {
//                System.out.println(oldPost);
//                updatePost.setId(id);
//                oldPost.setContent(updatePost.getContent());
//                oldPost.setTitle(updatePost.getTitle());
//                System.out.println(updatePost);
//            }
//        }
    }

    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody CreatePostDto dto) {
        Post newPost = new Post();
        postService.addPost(newPost, username, dto);
        emailService.prepareAndSend(newPost, "New Post Created", "You've created a new post.");
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePostById(id);
    }

    @GetMapping("search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postService.getPostsByTitleKeyword(keyword);
    }
}