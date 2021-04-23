package com.sergeyk.course.ws.userservice;

import com.sergeyk.course.ws.ui.model.request.UserDetailsRequestModel;
import com.sergeyk.course.ws.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
