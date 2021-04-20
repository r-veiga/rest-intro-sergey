package com.sergeyk.course.ws.ui.controller;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.sergeyk.course.ws.ui.model.request.UserDetailsRequestModel;
import com.sergeyk.course.ws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    // Emulate database with a Map for this course purposes
    private Map<String, UserRest> users = new HashMap<>();

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

    // Can receive header "Accept=application/xml"
    // as it produces either XML or JSON, depending on the request
    @GetMapping(path = "/{userId}",
                produces = { MediaType.APPLICATION_XML_VALUE,
                             MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if(users.containsKey(userId)) {
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(   consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
                    produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);

        users.put(userId, returnValue);

        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
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
