package com.ShoppingSite.service.userService;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.user.CustomUserRequest;
import com.ShoppingSite.repository.userRepository.UserRepository;
import com.ShoppingSite.service.shopInterfaceService.FavoriteService;
import com.ShoppingSite.service.shopInterfaceService.OrderService;
import com.ShoppingSite.service.shopInterfaceService.ShoppingCartService;
import com.ShoppingSite.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    OrderService orderService;
    @Autowired
    FavoriteService favoriteService;
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
       CustomUser customUser = userRepository.getUserByUsername(username);
        if (customUser != null){
            shoppingCartService.deleteShoppingCartByUsername(username);
            orderService.deleteOrderById(customUser.getId());
            favoriteService.deleteFavoriteOfUser(customUser.getId());
            userRepository.deleteUserByUsername(username);
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
    @Override
    public boolean checkUserExists(String username) {
        return userRepository.checkUserExists(username);
    }
    public Integer checkUserActiveStatusByUsername(String username){
        return userRepository.checkUserActiveStatusByUsername(username);
    }

    @Override
    public void loginUser(String username) {
        userRepository.loginUser(username);
    }

    public void logoutUser(String username){
        userRepository.logoutUser(username);
    }
}
