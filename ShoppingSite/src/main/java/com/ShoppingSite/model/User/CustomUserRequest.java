package com.ShoppingSite.model.User;

import com.ShoppingSite.model.User.CustomUser;

public class CustomUserRequest {
    private Long id;

    private String username;

    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    public CustomUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomUser toCustomUser() {
        return new CustomUser(
                null,
                this.username,
                this.password,
                "",
                "",
                "",
                "",
                "",
                1,
                "",
                ""
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
