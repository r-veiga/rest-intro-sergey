package com.sergeyk.course.ws.ui.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String getUser(@RequestParam(value="page") int page, @RequestParam(value="limit") int limit) {
        return "get user was called with page = " + page + " and limit = " + limit;
    }

    @GetMapping(path="/{userId}")
    public String getUser(@PathVariable String userId) {
        return "get user was called with path parameter " + userId;
    }

    @PostMapping
    public String createUser() {
        return "create user was called";
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

}