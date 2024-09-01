package com.ShoppingSite.service.shopInterfaceService;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.model.shopInterface.ShoppingCart;
import com.ShoppingSite.repository.productRepository.ProductRepository;
import com.ShoppingSite.repository.shopnIterfaceRepository.ShoppingCartRepository;
import com.ShoppingSite.repository.userRepository.UserRepository;
import com.ShoppingSite.utils.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FunctionUtil functionUtil;

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) throws Exception {
        if (!functionUtil.isUserExists(shoppingCart.getUsername())) {
            return null; // User does not exist
        }

        // Retrieve the user_id if it is not provided
        if (shoppingCart.getUserId() == null) {
            Integer userId = userRepository.getUserIdByUsername(shoppingCart.getUsername());
            if (userId == null) {
                throw new RuntimeException("User ID not found for username: " + shoppingCart.getUsername());
            }
            shoppingCart.setUserId(userId);
        }

        ShoppingCart existingCart = shoppingCartRepository.getShoppingCartByUsername(shoppingCart.getUsername());
        if (existingCart != null) {
            // Update existing cart
            return updateExistingCartWithNewProducts(existingCart, shoppingCart.getProductsList());
        } else {
            // Create new cart with products
            return createNewCart(shoppingCart);
        }
    }
    @Override
    public String addProductToCart(String username, Integer productId) throws Exception {
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID " + productId + " does not exist.");
        }

        Integer userId = userRepository.getUserIdByUsername(username);
        if (userId == null) {
            throw new RuntimeException("User ID not found for username: " + username);
        }
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUsername(username);
        if (shoppingCart == null) {
            // Create a new shopping cart if one does not exist
            shoppingCart = new ShoppingCart();
            shoppingCart.setUsername(username);
            shoppingCart.setUserId(userId);
            shoppingCart.setProductsList(new ArrayList<>());
            shoppingCart.setAmount(0.0);
            shoppingCart.setState(1);  // Default state to '1'
        }
        boolean productExistsInCart = false;
        for (Product cartProduct : shoppingCart.getProductsList()) {
            if (cartProduct.getId().equals(product.getId())) {
                // If the product already exists in the cart, update the quantity
                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
                productExistsInCart = true;
                break;
            }
        }
        if (!productExistsInCart) {
            // Add product to the cart if it doesn't already exist
            product.setQuantity(1); // Default to 1 if adding for the first time
            shoppingCart.getProductsList().add(product);
        }

        // Update total amount
        shoppingCart.setAmount(shoppingCart.getAmount() + product.getPrice());

        // Save or update the shopping cart in the repository
        if (shoppingCart.getCartId() == null) {
            shoppingCartRepository.createShoppingCart(shoppingCart);
            return "Shopping cart created and product added successfully.";
        } else {
            shoppingCartRepository.updateShoppingCart(shoppingCart);
            return "Shopping cart updated successfully.";
        }
    }

    @Override
    public String updateShoppingCart(Integer userId, String username, List<Product> productsList) throws Exception {
        if (!functionUtil.isUserExists(username)) {
            return "Error: User does not exist";
        }

        ShoppingCart existingCart = shoppingCartRepository.getShoppingCartByUsername(username);
        if (existingCart == null) {
            return createNewShoppingCart(userId, username, productsList);
        }

        return updateExistingCart(existingCart, productsList);
    }

    @Override
    public ShoppingCart getShoppingCartByUsername(String username) {
        if (!functionUtil.isUserExists(username)) {
            System.out.println("User " + username + " does not exist");
            return null;
        }

        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUsername(username);
        if (!shoppingCartRepository.checkIfCartExist(username)) {
            System.out.println("No shopping cart found for user " + username + ", creating new one");
            shoppingCart = new ShoppingCart();
            shoppingCart.setUsername(username);
            shoppingCart.setUserId(userRepository.getUserByUsername(username).getId());
            shoppingCart.setAmount(0.0);
            shoppingCart.setState(1);
            shoppingCart.setProductsList(new ArrayList<>());
            shoppingCartRepository.createShoppingCart(shoppingCart);
        }

        return shoppingCart;
    }

    @Override
    public int updateProductInCart(Integer cartId, Integer productId, int quantity) {
        return 0; // Implementation left for further development
    }

    @Override
    public Boolean deleteShoppingCartByUsername(String username) {
        if (functionUtil.isUserExists(username)) {
            int dbShoppingCart = shoppingCartRepository.deleteShoppingCartByUsername(username);
            return dbShoppingCart > 0;
        } else {
            System.out.println("User " + username + " does not exist");
            return false;
        }
    }

    public boolean checkIfCartExist(String username) {
        return shoppingCartRepository.checkIfCartExist(username);
    }

//   ---------------------------------------------- Helper methods -------------------------------------------------------
    private Product getFullProductDetails(Product product) {
        Product existingProduct = productRepository.getProductByName(product.getProductName());
        if (existingProduct == null) {
            throw new RuntimeException("Product " + product.getProductName() + " does not exist.");
        }
        existingProduct.setQuantity(product.getQuantity());
        return existingProduct;
    }

    private String createNewShoppingCart(Integer userId, String username, List<Product> productsList) {
        ShoppingCart newCart = new ShoppingCart();
        newCart.setUserId(userId);
        newCart.setUsername(username);
        newCart.setProductsList(productsList);

        // Calculate the total amount
        double totalAmount = calculateTotalAmount(productsList);
        newCart.setAmount(totalAmount);

        // Set the default state if not provided
        if (newCart.getState() == null) {
            newCart.setState(1); // default to 1 if state is null
        }

        shoppingCartRepository.createShoppingCart(newCart);
        return "Shopping cart created and product added successfully.";
    }

    private String updateExistingCart(ShoppingCart existingCart, List<Product> productsList) throws Exception {
        double totalAmount = existingCart.getAmount();
        List<Product> updatedProductsList = existingCart.getProductsList();

        for (Product product : productsList) {
            updateProductDetails(product);
            updateCartProductList(updatedProductsList, product);
            totalAmount += product.getPrice() * product.getQuantity();
        }

        existingCart.setAmount(totalAmount);
        existingCart.setProductsList(updatedProductsList);
        shoppingCartRepository.updateShoppingCart(existingCart);

        return "Shopping cart updated successfully.";
    }

    private void updateProductDetails(Product product) throws Exception {
        if (product.getProductName() == null) {
            Product existingProduct = productRepository.getProductById(product.getId());
            if (existingProduct == null) {
                throw new RuntimeException("Error: Product with ID " + product.getId() + " does not exist.");
            }
            product.setProductName(existingProduct.getProductName());
            product.setPrice(existingProduct.getPrice());
            product.setStatus(existingProduct.getStatus());
        }

        if (product.getQuantity() > productRepository.getProductByName(product.getProductName()).getQuantity()) {
            throw new RuntimeException("Error: Not enough quantity for product " + product.getProductName());
        }

        productRepository.updateProductQuantity(product);
    }

    private void updateCartProductList(List<Product> updatedProductsList, Product product) {
        boolean foundInCart = false;
        for (Product cartProduct : updatedProductsList) {
            if (cartProduct.getProductName().equals(product.getProductName())) {
                int newQuantity = cartProduct.getQuantity() + product.getQuantity();
                cartProduct.setQuantity(newQuantity);
                foundInCart = true;
                break;
            }
        }

        if (!foundInCart) {
            updatedProductsList.add(product);
        }
    }

    private double calculateTotalAmount(List<Product> productsList) {
        double totalAmount = 0.0;

        for (Product product : productsList) {
            totalAmount += product.getPrice() * product.getQuantity();
            Product dbProduct = productRepository.getProductByName(product.getProductName());

            if (dbProduct.getQuantity() < product.getQuantity()) {
                throw new RuntimeException("Not enough quantity for product " + product.getProductName());
            }

            int newQuantity = dbProduct.getQuantity() - product.getQuantity();
            dbProduct.setQuantity(newQuantity);
            productRepository.updateProductQuantity(dbProduct);
        }

        return totalAmount;
    }

    private ShoppingCart createNewCart(ShoppingCart shoppingCart) {
        double totalAmount = calculateTotalAmount(shoppingCart.getProductsList());
        shoppingCart.setAmount(totalAmount);

        if (shoppingCart.getState() == null) {
            shoppingCart.setState(1); // default to 1 if state is null
        }

        shoppingCartRepository.createShoppingCart(shoppingCart);
        return shoppingCart;
    }

    private ShoppingCart updateExistingCartWithNewProducts(ShoppingCart existingCart, List<Product> newProductsList) throws Exception {
        double totalAmount = existingCart.getAmount();
        List<Product> updatedProductsList = existingCart.getProductsList();

        for (Product product : newProductsList) {
            updateProductDetails(product);
            updateCartProductList(updatedProductsList, product);
            totalAmount += product.getPrice() * product.getQuantity();
        }

        existingCart.setAmount(totalAmount);
        existingCart.setProductsList(updatedProductsList);
        shoppingCartRepository.updateShoppingCart(existingCart);

        return existingCart;
    }
}
