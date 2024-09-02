package com.ShoppingSite.unsplash;

import com.ShoppingSite.model.product.Product;
import com.ShoppingSite.service.productService.ProductService;
import com.ShoppingSite.unsplash.model.UnsplashResponse;
import com.ShoppingSite.utils.ApiImageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsplash")
public class UnsplashController {

    @Autowired
    UnsplashService unsplashService;
    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public UnsplashResponse getPhotos(@RequestParam String query) {
        return unsplashService.searchPhotos(query, ApiImageParams.ACCESS_KEY);
    }

    @GetMapping("/assignImageToProduct")
    public String assignImageToProduct(@RequestParam String productName) {
        UnsplashResponse response = unsplashService.searchPhotos(productName, ApiImageParams.ACCESS_KEY);
        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            String imageUrl = response.getResults().get(0).getUrls().getRegular();
            Product product = productService.getProductByName(productName);
            if (product != null) {
                product.setImageUrl(imageUrl);
                productService.updateProductByName(productName, product);
                return "Image assigned successfully!";
            } else {
                return "Product not found";
            }
        } else {
            return "No images found";
        }
    }
}
