package com.ShoppingSite.unsplash;

import com.ShoppingSite.unsplash.model.UnsplashResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "UnsplashService",
        url = "${unsplash.url}"
)
public interface UnsplashService {

    @GetMapping("/search/photos")
    UnsplashResponse searchPhotos(@RequestParam("query") String query,
                                  @RequestParam("client_id") String clientId);
}