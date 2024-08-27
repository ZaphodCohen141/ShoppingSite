package com.ShoppingSite.utils;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.repository.userRepository.UserRepository;
import com.ShoppingSite.unsplash.UnsplashService;
import com.ShoppingSite.unsplash.model.UnsplashResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class FunctionUtil {
    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private UnsplashService unsplashService;
    @Autowired
    @Lazy
    private ApiImageParams apiImageParams;

    public String generateSqlSetString(Object ob) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = ob.getClass().getDeclaredFields();
        // loop through the fields and create SET string to post to the DB
        sb.append(" SET ");

        boolean firstField = true;
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(ob);
                if (value != null) {
                    if (!firstField) {
                        sb.append(", ");
                    }
                    sb.append(field.getName())
                            .append(" = '")
                            .append(value)
                            .append("'");
                    firstField = false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field value", e);
            }
        }

        return sb.toString();
    }

    // check if a user exists
    public boolean isUserExists(String username) {
        CustomUser user = userRepository.getUserByUsername(username);
        return user != null;
    }

    // get random image from unsplash if no photo found for the product
    public String getImageForProduct(String productName) {
        UnsplashResponse response = unsplashService.searchPhotos(productName, apiImageParams.ACCESS_KEY);

        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            String fullImageUrl = response.getResults().get(0).getUrls().getRegular();
            return fullImageUrl.split("\\?")[0];
        } else {
            return getRandomImageUrl();
        }
    }

    private String getRandomImageUrl() {
        UnsplashResponse response = unsplashService.searchPhotos("random", apiImageParams.ACCESS_KEY);
//        get random pic if no pic returns use placeholder
        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            String fullImageUrl = response.getResults().get(0).getUrls().getRegular();
            return fullImageUrl.split("\\?")[0];
        } else {
            return "https://via.placeholder.com/150";
        }
    }
}
