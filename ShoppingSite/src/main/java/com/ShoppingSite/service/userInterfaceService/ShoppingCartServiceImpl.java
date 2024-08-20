package com.ShoppingSite.service.userInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.userInterface.ShoppingCart;
import com.ShoppingSite.repository.productRepository.ProductRepository;
import com.ShoppingSite.repository.userInterfaceRepository.ShoppingCartRepository;
import com.ShoppingSite.repository.userRepository.UserRepository;
import com.ShoppingSite.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    FunctionUtil functionUtil;


    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        if (!functionUtil.isUserExists(shoppingCart.getUsername())) {
            return null; // User does not exist
        }
        // Check if cart already exists
        ShoppingCart existingCart = shoppingCartRepository.getShoppingCartByUsername(shoppingCart.getUsername());
        if (existingCart != null) {
            // Update existing cart
            shoppingCart.setCartId(existingCart.getCartId());
            shoppingCartRepository.updateShoppingCart(shoppingCart);
            return shoppingCart;
        } else {
            // insert product to cart with the full product details
            List<Product> fullProductsList = shoppingCart.getProductsList().stream()
                    .map(this::getFullProductDetails)
                    .collect(Collectors.toList());
            shoppingCart.setProductsList(fullProductsList);
            // calc total amount and update product quantities
            double totalAmount = 0.0;
            for (Product product : shoppingCart.getProductsList()) {
                totalAmount += product.getPrice() * product.getQuantity();
                Product dbProduct = productRepository.getProductByName(product.getProductName());
                // Check if enough quantity available
                if (dbProduct.getQuantity() < product.getQuantity()) {
                    throw new RuntimeException("Not enough quantity for product " + product.getProductName());
                }
                // Deduct cart quantity from current quantity
                int newQuantity = dbProduct.getQuantity() - product.getQuantity();
                dbProduct.setQuantity(newQuantity);
                // update product quantity in the database
                productRepository.updateProductQuantity(dbProduct);
            }
            shoppingCart.setAmount(totalAmount);
            if (shoppingCart.getState() == null) {
                shoppingCart.setState(1); // Default to 1 if state is null
            }
            shoppingCartRepository.createShoppingCart(shoppingCart);
            return shoppingCart;
        }
    }

    @Override
    public String updateShoppingCart(Integer userId, String username, List<Product> productsList) {
        if (!functionUtil.isUserExists(username)) {
            return "Error: User does not exist";
        }
        ShoppingCart existingCart = shoppingCartRepository.getShoppingCartByUsername(username);
        if (existingCart == null) {
            return "Error: Shopping cart does not exist for this user";
        }
        // calc new total amount and update product quantities
        double totalAmount = existingCart.getAmount();
        List<Product> updatedProductsList = existingCart.getProductsList();
        for (Product product : productsList) {
            Product existingProduct = productRepository.getProductByName(product.getProductName());
            if (existingProduct == null) {
                return "Error: Product " + product.getProductName() + " does not exist";
            }
            // check if in existing cart
            boolean foundInCart = false;
            for (Product cartProduct : updatedProductsList) {
                if (cartProduct.getProductName().equals(product.getProductName())) {
                    // if already in cart, update quantity and total amount
                    int newQuantity = cartProduct.getQuantity() + product.getQuantity();
                    cartProduct.setQuantity(newQuantity);
                    totalAmount += existingProduct.getPrice() * product.getQuantity();
                    foundInCart = true;
                    break;
                }
            }
            if (!foundInCart) {
                // if product not in cart, add it
                Product newProduct = new Product(
                        existingProduct.getId(),
                        existingProduct.getProductName(),
                        product.getQuantity(),
                        existingProduct.getPrice(),
                        existingProduct.getStatus()
                );
                updatedProductsList.add(newProduct);
                totalAmount += newProduct.getPrice() * newProduct.getQuantity();
            }
            if (existingProduct.getQuantity() < product.getQuantity()) {
                return "Error: Not enough quantity for product " + product.getProductName() + ".";
            }
            //    update new totals
            existingProduct.setQuantity(existingProduct.getQuantity() - product.getQuantity());
            productRepository.updateProductQuantity(existingProduct);
        }
        existingCart.setAmount(totalAmount);
        existingCart.setProductsList(updatedProductsList);
        shoppingCartRepository.updateShoppingCart(existingCart);
        return "Shopping cart updated successfully.";
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
            // user not exist
            System.out.println("user " + username + " does not exist");
            return false;
        }
    }

//    --------------------------------------------- Helper Methods ---------------------------------------------
// get full product object from DB
    private Product getFullProductDetails(Product product) {
        Product existingProduct = productRepository.getProductByName(product.getProductName());
        if (existingProduct == null) {
            throw new RuntimeException("Product " + product.getProductName() + " does not exist.");
        }
        existingProduct.setQuantity(product.getQuantity());
        return existingProduct;
    }
}
