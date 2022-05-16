package com.example.restblog.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/jason")
public class PostsController {

    @GetMapping
    public List<Post> getAll(){
        List<Post> newPosts = new ArrayList<>();
        newPosts.add(new Post(1, "Dog pics", " this is a picture of my dog"));
        newPosts.add(new Post(2,"Dinner", "Dinner "));
        return newPosts;
    }

}
