package com.ShoppingSite.controller.user;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.user.CustomUserRequest;
import com.ShoppingSite.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public Integer createUser(@RequestBody CustomUserRequest customUserRequest) throws Exception {
        return userService.createUser(customUserRequest);
    }
//    @CrossOrigin
//    public ResponseEntity<?> createUser(@RequestBody CustomUserRequest customUser){
//        try{
//            userService.createUser(customUser);
//            return null;
//        } catch (Exception exception){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
//        }
//    }
    @GetMapping("/getUserByUsername")
    public CustomUser getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }
    @DeleteMapping("/deleteUser")
    public void deleteUserByUsername(@RequestParam String username) {
        userService.deleteUserByUsername(username);
    }
    @PostMapping("/updateUserByUsername")
    public String updateUserByUsername(@RequestParam String username, @RequestBody CustomUser customUser) {
        return userService.updateUserByUsername(username,customUser);
    }

    public CustomUser findUserByUsername(@RequestParam String username) {
        return findUserByUsername(username);
    }
}
