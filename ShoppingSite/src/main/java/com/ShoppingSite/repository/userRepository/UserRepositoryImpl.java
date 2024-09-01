package com.ShoppingSite.repository.userRepository;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.repository.userRepository.userMapper.CustomUserMapper;
import com.ShoppingSite.utils.FunctionUtil;
import com.ShoppingSite.utils.TableNamesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FunctionUtil functionUtil;
    @Override
    public Integer createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + TableNamesUtil.USER_TABLE_NAME + " (username, firstName, lastName, " +
                "email, phone, address, password, active, roles , permissions) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,customUser.getUsername(),customUser.getFirstName(), customUser.getLastName(),
                    customUser.getEmail(),customUser.getPhone(),customUser.getAddress(),customUser.getPassword(),
                    customUser.getActive(),customUser.getRoles(),customUser.getPermissions());
        return customUser.getId();
    }

    @Override
    public CustomUser getUserByUsername(String username) {
        String sql = "SELECT * FROM " +
                TableNamesUtil.USER_TABLE_NAME +
                " WHERE username = ?";
        try {
            CustomUser customUser = jdbcTemplate.queryForObject(sql, new CustomUserMapper(),username);
            return customUser;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deleteUserByUsername(String username) {
        String sql = "DELETE FROM " +
                TableNamesUtil.USER_TABLE_NAME +
                " WHERE username = ?";
        jdbcTemplate.update(sql,username);
    }

    @Override
    public String updateUserByUsername(String username, CustomUser customUser) {
        String sql = "UPDATE " +
                TableNamesUtil.USER_TABLE_NAME +
                functionUtil.generateSqlSetString(customUser) +
                " WHERE username = ?";
        Integer update = jdbcTemplate.update(sql,username);
        if (update == 1){
            return ("user " + username + " was updated");
        }else {
            return ("There isn't user named " + username);
        }
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        String sql = "SELECT * FROM " + TableNamesUtil.USER_TABLE_NAME + " WHERE userName = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CustomUserMapper(), username);
        } catch (EmptyResultDataAccessException error){
            return null;
        }
    }

    @Override
    public boolean checkUserExists(String username) {
        String sql = "SELECT COUNT(*) FROM " + TableNamesUtil.USER_TABLE_NAME +
                " WHERE LOWER(username) LIKE LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public Integer checkUserActiveStatusByUsername(String username) {
        String sql = "SELECT active FROM " + TableNamesUtil.USER_TABLE_NAME +
                " WHERE LOWER(username) = LOWER(?)";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void loginUser(String username){
        String sql = "UPDATE " + TableNamesUtil.USER_TABLE_NAME +
                " SET active = 1 WHERE LOWER(username) = LOWER(?)";
        jdbcTemplate.update(sql,username);
    }
    @Override
    public void logoutUser(String username) {
        String sql = "UPDATE " + TableNamesUtil.USER_TABLE_NAME +
                " SET active = 0 WHERE LOWER(username) = LOWER(?)";
        jdbcTemplate.update(sql,username);
    }
}
