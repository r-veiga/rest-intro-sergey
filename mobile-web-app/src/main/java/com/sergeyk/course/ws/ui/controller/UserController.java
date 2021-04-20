package com.sergeyk.course.ws.ui.controller;

import com.sergeyk.course.ws.ui.model.response.UserRest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    // Optional parameters could be used with either:
    //  (1) defaultValue = "xxxx"
    //  (2) required = false
    // but "required" has issues when dealing with not initialized primitives (help it adding "defaultValue")
    // returns a 500 error status due to IllegalStateException
    // Objects not initialized are set to null and there's no 500 error status but 200 Ok status
    @GetMapping
    public String getUser(@RequestParam(value="page", defaultValue="1") String page,
                          @RequestParam(value="limit", defaultValue="50", required=false) int limit,
                          @RequestParam(value="sort", required=false) String sort) {
        return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path="/{userId}")
    public UserRest getUser(@PathVariable String userId) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail("test@test.com");
        returnValue.setFirstName("Sergey");
        returnValue.setLastName("Kargopolov");
        return returnValue;
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
