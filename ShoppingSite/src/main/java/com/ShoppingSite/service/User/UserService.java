package com.ShoppingSite.service.User;

import com.ShoppingSite.model.User.CustomUser;
import com.ShoppingSite.model.User.CustomUserRequest;

public interface UserService {
    void createUser(CustomUserRequest customUser) throws Exception;
    CustomUser findUserByUsername(String username);
}
