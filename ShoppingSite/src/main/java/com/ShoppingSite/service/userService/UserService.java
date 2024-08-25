package com.ShoppingSite.service.userService;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.user.CustomUserRequest;

public interface UserService {
    public Integer createUser(CustomUserRequest customUserRequest) throws Exception;
    public CustomUser getUserByUsername (String username);
    public void deleteUserByUsername (String username);
    public String updateUserByUsername (String username, CustomUser customUser);
    public CustomUser findUserByUsername(String username);
}
