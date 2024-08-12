package com.ShoppingSite.repository.userReository;

import com.ShoppingSite.model.User.CustomUser;

public interface UserRepository {
    public Long createUser(CustomUser customUser);
    public CustomUser getUserByUsername (String username);
    public void deleteUserByUsername (String username);
    public void updateUserByUsername (String username);
    public CustomUser findUserByUsername(String username);
}
