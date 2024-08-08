package com.ShoppingSite.repository;

import com.ShoppingSite.model.CustomUser;
import com.ShoppingSite.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private static final String USER_TABLE_NAME = "users";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " (id, userName, firstName," +
                " lastName, email, phone, address, password, active, roles, permissions) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,customUser.getId(), customUser.getUsername(), customUser.getFirstName(),customUser.getLastName(),
                customUser.getEmail(),customUser.getPhone(),customUser.getAddress(), customUser.getPassword(),
                customUser.getActive(),customUser.getRoles(),customUser.getPermissions());
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        String sql = "SELECT * FROM " + USER_TABLE_NAME + " WHERE userName = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException error){
            return null;
        }
    }
}
