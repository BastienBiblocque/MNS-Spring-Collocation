package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/user", produces="application/json")
@CrossOrigin(origins="*")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
    @GetMapping("")
    public Object get() {
        User toto = new User("toto");

//        toto.setName("toto");

        repository.save(toto);

        return repository.findAll();
    }
}