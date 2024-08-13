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
        System.out.println(sql);
        System.out.println("Inserting user: " + customUser.toString());
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
        System.out.println(username);
        jdbcTemplate.update(sql,username);
    }

    @Override
    public String updateUserByUsername(String username, CustomUser customUser) {
        String sql = "UPDATE " +
                TableNamesUtil.USER_TABLE_NAME +
                functionUtil.generateSqlSetString(customUser) +
                " WHERE username = ?";
        System.out.println(sql);
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
}
