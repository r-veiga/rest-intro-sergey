package com.sergeyk.course.ws.ui.controller;

import com.sergeyk.course.ws.exceptions.Type1Exception;
import com.sergeyk.course.ws.exceptions.Type2Exception;
import com.sergeyk.course.ws.exceptions.UserServiceException;
import com.sergeyk.course.ws.ui.model.request.UpdateUserDetailRequestModel;
import com.sergeyk.course.ws.ui.model.request.UserDetailsRequestModel;
import com.sergeyk.course.ws.ui.model.response.UserRest;
import com.sergeyk.course.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {

    // Emulate database with a Map for this course purposes
    private Map<String, UserRest> users = new HashMap<>();

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @PostMapping(
        consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
        produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    // Dealing PUT with Spring Boot needs "FormContentFilter" configured
    // https://stackoverflow.com/questions/5894270/springmvc-is-not-recognizing-request-body-parameters-if-using-put
    @PutMapping(
        path = "/{userId}",
        consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
        produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public UserRest updateUser(
        @PathVariable String userId,
        @Valid @RequestBody UpdateUserDetailRequestModel userDetails
    ) {
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());
        users.put(userId, storedUserDetails);
        return storedUserDetails;
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        users.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(
        path = "exception/{userId}",
        produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<UserRest> generateException(@PathVariable String userId) {

        //--- (3)
        if(true) throw new Type2Exception("A Type 2 exception is thrown");
        if(true) throw new Type1Exception("A Type 1 exception is thrown");
        //--- (2)
        if(true) throw new UserServiceException("A user service exception is thrown");
        //--- (1)
        String firstName = null;
        // ... blah
        // ... bleh
        int firstNameLength = firstName.length(); // throws NullPointerException

        if(users.containsKey(userId)) {
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }
}
