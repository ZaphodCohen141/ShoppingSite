package com.ShoppingSite.service.userInterfaceService;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.userInterface.ShoppingCart;
import com.ShoppingSite.repository.userInterfaceRepository.ShoppingCartRepository;
import com.ShoppingSite.repository.userRepository.UserRepository;
import com.ShoppingSite.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    FunctionUtil functionUtil;
    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        if (functionUtil.isUserExists(shoppingCart.getUsername())) {
            ShoppingCart existingCart = shoppingCartRepository.getShoppingCartByUsername(shoppingCart.getUsername());
            if (existingCart != null) {
//                if cart already exist
                shoppingCart.setCartId(existingCart.getCartId());
                shoppingCartRepository.updateShoppingCart(shoppingCart);
                return shoppingCart;
            } else {
                shoppingCartRepository.createShoppingCart(shoppingCart);
                return shoppingCart;
            }
        } else {
            // user does not exist
            return null;
        }
    }

    @Override
    public Integer updateShoppingCart(ShoppingCart shoppingCart) {
        // Check if the user exists
        if (functionUtil.isUserExists(shoppingCart.getUsername())) {
            // Check if the user already has a cart
            ShoppingCart existingCart = shoppingCartRepository.getShoppingCartByUsername(shoppingCart.getUsername());
            if (existingCart != null) {
                // Update the existing cart
                shoppingCart.setCartId(existingCart.getCartId()); // Retain the existing cart ID
                System.out.println("Shopping cart updated successfully");
                return shoppingCartRepository.updateShoppingCart(shoppingCart);
            } else {
                System.out.println("no existing shopping cart found for this user");
                return shoppingCartRepository.updateShoppingCart(shoppingCart);
            }
        } else {
            System.out.println("user does not exist");
            return shoppingCartRepository.updateShoppingCart(shoppingCart);
        }
    }

    @Override
    public ShoppingCart getShoppingCartByUsername(String username) {
        if (functionUtil.isUserExists(username)) {
            return shoppingCartRepository.getShoppingCartByUsername(username);
        } else {
            //user does not exist
            System.out.println("user " + username + " does not exist");
            return null;
        }
    }

    @Override
    public Boolean deleteShoppingCartByUsername(String username) {
        if (functionUtil.isUserExists(username)) {
            int dbShoppingCart = shoppingCartRepository.deleteShoppingCartByUsername(username);
            return dbShoppingCart > 0;
        } else {
            // user does not exist
            System.out.println("user " + username + " does not exist");
            return false;
        }
    }
}
