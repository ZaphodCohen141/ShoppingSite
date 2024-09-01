package com.ShoppingSite.repository.userRepository;

import com.ShoppingSite.model.user.CustomUser;

public interface UserRepository {
    public Integer createUser(CustomUser customUser);
    public CustomUser getUserByUsername (String username);
    public void deleteUserByUsername (String username);
    public String updateUserByUsername (String username, CustomUser customUser);
    public CustomUser findUserByUsername(String username);
    public boolean checkUserExists(String username);
    public Integer checkUserActiveStatusByUsername(String username);
    public void loginUser(String username);
    public void logoutUser(String username);
}
