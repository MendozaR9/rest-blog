package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UserController {
    ArrayList<User> newUsers = new ArrayList<>();

    @GetMapping
    public ArrayList<User> getAll(){
        newUsers.clear();
        newUsers.add(new User(1, "testUser", "user@gmail.com",
                "password", new Date(1000, 7, 5), User.Role.USER));
        newUsers.add(new User(2, "Duck", "duckman@gmail.com", "DuckMan",
                new Date(2000,5,7), User.Role.ADMIN));
        return newUsers;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable long id){
        for (User user: getAll()) {
            if (Objects.equals(user.getId(), id)){
                return user;
            }
        }
        return new User();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        System.out.println(user);
    }
    
    @PutMapping("{id}")
    public void  updateUser(@PathVariable long id, User user){
        for (User oldUser: getAll()) {
            if (Objects.equals(oldUser.getId(), id)){
                System.out.println(oldUser);
                user.setId(id);
                oldUser.setUsername(user.getUsername());
                oldUser.setEmail(user.getEmail());
                System.out.println(user);
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        for (User UserToDelete: getAll()) {
            if (Objects.equals(UserToDelete.getId(),id))
                System.out.println(UserToDelete+" will be deleted");
            getAll().remove(UserToDelete);
        }
    }

}
