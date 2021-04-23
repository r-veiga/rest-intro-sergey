package com.sergeyk.course.ws.userservice.impl;

import com.sergeyk.course.ws.ui.model.request.UserDetailsRequestModel;
import com.sergeyk.course.ws.ui.model.response.UserRest;
import com.sergeyk.course.ws.userservice.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Map<String, UserRest> users = new HashMap<>();

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);

        users.put(userId, returnValue);

        return returnValue;
    }
}
