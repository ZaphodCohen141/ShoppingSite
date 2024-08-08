package com.ShoppingSite.service;

import com.ShoppingSite.model.CustomUser;
import com.ShoppingSite.model.CustomUserRequest;

public interface UserService {
    void createUser(CustomUserRequest customUser) throws Exception;
    CustomUser findUserByUsername(String username);
}
