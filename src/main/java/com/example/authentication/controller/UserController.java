package com.example.authentication.controller;

import com.example.authentication.entity.User;
import com.example.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> fetchAll(){
       return new ResponseEntity(userService.selectAll(),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<User> create(@RequestBody User user){
        User persistedUser  = userService.saveUser(user);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/user/create").toUriString());
      return  ResponseEntity.created(uri).body(persistedUser);
    }

  /*  @PostMapping("/saveAll")
    public ResponseEntity<List<User>> create(@RequestBody List<User> users){
        List<User> persistedUsers  = userService.saveUsers(users);
        URI uri = URI
                .create(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/user/create").toUriString());
        return  ResponseEntity.created(uri).body(persistedUsers);
    }*/

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity("Welcome USer", HttpStatus.OK);
    }
}
