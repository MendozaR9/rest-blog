package com.example.restblog.data;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;;

@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    @GetMapping
    public ArrayList<Post> getAll() {
        ArrayList<Post> newPosts = new ArrayList<>();
        newPosts.add( new Post(1, "Dog pics", " this is a picture of my dog"));
        newPosts.add(new Post(2, "Dinner", "Dinner "));
        return newPosts;
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id){

        return new Post(id, "Dinner", "Dinner ");

    }

}
