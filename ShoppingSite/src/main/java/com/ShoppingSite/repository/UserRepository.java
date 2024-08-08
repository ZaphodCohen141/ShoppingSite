package com.ShoppingSite.repository;

import com.ShoppingSite.model.CustomUser;

public interface UserRepository {
    void createUser(CustomUser customUser);

    CustomUser findUserByUsername(String username);
}
