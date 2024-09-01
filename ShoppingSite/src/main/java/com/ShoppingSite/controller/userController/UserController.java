package com.ShoppingSite.controller.userController;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.model.user.CustomUserRequest;
import com.ShoppingSite.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Integer> createUser(@RequestBody CustomUserRequest customUserRequest) {
        try {
            Integer userId = userService.createUser(customUserRequest);
            return ResponseEntity.ok(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
    @DeleteMapping("/delete")
    public void deleteUserByUsername(@RequestParam String username) {
        userService.deleteUserByUsername(username);
    }
    @PostMapping("/updateUserByUsername")
    public String updateUserByUsername(@RequestParam String username, @RequestBody CustomUser customUser) {
        return userService.updateUserByUsername(username,customUser);
    }
    @GetMapping("/findUser")
    public CustomUser findUserByUsername(@RequestParam String username) {
        return findUserByUsername(username);
    }
    @GetMapping("/checkUserExists")
    public ResponseEntity<Boolean> checkUserExists(@RequestParam String username) {
        boolean exists = userService.checkUserExists(username);
        System.out.println("User exists: " + exists);
        return ResponseEntity.ok(exists);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody CustomUserRequest loginRequest) {
        CustomUser user = userService.getUserByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            userService.loginUser(user.getUsername());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }
    }

    @GetMapping("/user_status/{username}")
    public Integer getUserActiveStatus(@PathVariable String username) {
        return userService.checkUserActiveStatusByUsername(username);
    }

    @PostMapping("/logout/{username}")
    public void logoutUser(@PathVariable String username) {
        userService.logoutUser(username);
    }
}
