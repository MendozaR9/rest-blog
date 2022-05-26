package com.example.restblog.web;
import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.services.UserService;
import com.example.restblog.web.dto.UpdateUserDto;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UserController {
    private final UserService userServices;

    public UserController(UserService userServices) {
        this.userServices = userServices;
    }


    @GetMapping
    public List<User> getAll(){
        return userServices.getAllUsers();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable long id){
        for (User user: userServices.getAllUsers()) {
            if (Objects.equals(user.getId(), id)){
                return user;
            }
        }
        return null;
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username){
        for (User user: userServices.getAllUsers()) {
            if (Objects.equals(user.getUsername(), username)){
                return user;
            }
        }
        return null;
    }


    @GetMapping("email")
    public User getByEmail(@RequestParam String email){
        for (User user: userServices.getAllUsers()) {
            if (Objects.equals(user.getEmail(), email)){
                return user;
            }
        }
        return null;
    }

    @PostMapping("newPost/{username}")
    public void addUserPost(@PathVariable String username, @RequestBody Post newPost){
        User user = userServices.getByUsername(username);
        user.getPosts().add(newPost);
    }

    @PostMapping
    public void create(@RequestBody User user){
    userServices.getAllUsers().add(user);
    System.out.println(user);
    }
    
    @PutMapping("{id}")
    public void  update(@PathVariable long id, @RequestBody UpdateUserDto updateUserDto){
        userServices.updateUser(updateUserDto);
        System.out.println(updateUserDto);

    }

    @PutMapping("{id}/updatePassword")
    public void updatePassword(   @PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
            User userToUpdate = getById(id);
            userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        System.out.println("Deleting the  user with the id of "+ id);
    }

}
