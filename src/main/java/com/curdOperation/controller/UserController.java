package com.curdOperation.controller;

import com.curdOperation.entity.User;
import com.curdOperation.exception.ResourceNotFoundException;
import com.curdOperation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User serviceUser = userService.createUser(user);
        return new ResponseEntity<>(serviceUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public  ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return  new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User userById = userService.getUserById(id);
        if(userById != null){
            return  new ResponseEntity<>(userById,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(userById, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        User UpdatedUser = userService.updateUser(user, id);
        if (UpdatedUser != null){
            return new ResponseEntity<>(UpdatedUser, HttpStatus.OK);
        }else {
            return  new ResponseEntity<>(UpdatedUser,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User with ID " + id + " deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
