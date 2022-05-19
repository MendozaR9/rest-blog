package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        if (newUsers.size() <= 3) {
            newUsers.add(new User(1, "testUser", "user@gmail.com", "password"));
            newUsers.add(new User(2, "Duck", "duckman@gmail.com", "DuckMan"));
        }
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

    @GetMapping("/username")
    public User getByUsername(@RequestParam String username){
        for (User user: getAll()) {
            if (Objects.equals(user.getUsername(), username)){
                return user;
            }
        }
        return new User();
    }

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        for (User user: getAll()) {
            if (Objects.equals(user.getEmail(), email)){
                return user;
            }
        }
        return new User();
    }

    @PostMapping
    public void create(@RequestBody User user){
        newUsers.add(user);
        System.out.println(user);
    }
    
    @PutMapping("{id}")
    public void  update(@PathVariable long id,  @RequestBody User user){
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
//        for (User UserToDelete: getAll()) {
//            if (Objects.equals(UserToDelete.getId(),id))
//                System.out.println(UserToDelete+" will be deleted");
//            getAll().remove(UserToDelete);
//        }
        System.out.println("Deleting the  user with the id of "+ id);
    }

}
