package com.ShoppingSite.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomUserRequest {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("address")
    private String address;
    @JsonProperty("password")
    private String password;
    @JsonProperty("active")
    private int active;
    @JsonProperty("roles")
    private String roles = "";
    @JsonProperty("permissions")
    private String permissions = "";

    public CustomUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomUser toCustomUser(){
        return new CustomUser(
        this.username = username,
        this.firstName = firstName,
        this.lastName = lastName,
        this.email = email,
        this.phone = phone,
        this.address = address,
        this.password = password,
        this.active = active,
        this.roles = roles,
        this.permissions = permissions
        );
    }
//    public CustomUser toCustomUser() {
//        return new CustomUser(
//                null,
//                this.username,
//                this.password,
//                "",
//                "",
//                "",
//                "",
//                "",
//                1,
//                "",
//                ""
//        );
//    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
