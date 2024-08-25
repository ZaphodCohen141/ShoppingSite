package com.ShoppingSite.service.userService;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.user.CustomUserRequest;
import com.ShoppingSite.repository.userRepository.UserRepository;
import com.ShoppingSite.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FunctionUtil functionUtil;
    @Override
    public Integer createUser(CustomUserRequest customUserRequest) throws Exception {
        CustomUser existingCustomUser = userRepository.findUserByUsername(customUserRequest.getUsername());
        if(existingCustomUser != null){
            throw new Exception("Username " + customUserRequest.getUsername() + " is already taken");
        }
        return userRepository.createUser(customUserRequest.toCustomUser());
    }

    @Override
    public CustomUser getUserByUsername(String username) {
        CustomUser customUser = userRepository.getUserByUsername(username);
        if (customUser != null){
            return userRepository.getUserByUsername(username);
        }else {
            System.out.println("there is no such user " + username);
            return null;
        }
    }

    @Override
    public void deleteUserByUsername(String username) {
//        checks if product exists.
//        if exists - delete the product (!! need to be deleted also from userShoppingCart !!)
       CustomUser customUser = userRepository.getUserByUsername(username);
        if (customUser != null){
            userRepository.deleteUserByUsername(username);
//      !! ADD LATER - DELETE USER FROM SHOPPING CART + PERMISSIONS + OTHER COMPONENTS !!
            System.out.println("user was deleted");

        }else {
            System.out.println("there is no such user " + username);
        }
    }

    @Override
    public String updateUserByUsername(String username, CustomUser customUser) {
        CustomUser customUserFromDb = userRepository.getUserByUsername(username);
        if (customUserFromDb != null){
            return userRepository.updateUserByUsername(username,customUser);
        } else {
            System.out.println("there is no such user " + username);
            return null;
        }
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
