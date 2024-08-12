package com.ShoppingSite.repository.userReository;

import com.ShoppingSite.model.User.CustomUser;
import com.ShoppingSite.repository.userReository.userMapper.UserMapper;
import com.ShoppingSite.utils.TableNamesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Long createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + TableNamesUtil.USER_TABLE_NAME + " (userName, firstName," +
                " lastName, email, phone, address, password, active, roles, permissions) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,customUser.getUsername(), customUser.getFirstName(),customUser.getLastName(),
                customUser.getEmail(),customUser.getPhone(),customUser.getAddress(), customUser.getPassword(),
                customUser.getActive(),customUser.getRoles(),customUser.getPermissions());
        return customUser.getId();
    }

    @Override
    public CustomUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public void deleteUserByUsername(String username) {

    }

    @Override
    public void updateUserByUsername(String username) {

    }

    @Override
    public CustomUser findUserByUsername(String username) {
        String sql = "SELECT * FROM " + TableNamesUtil.USER_TABLE_NAME + " WHERE userName = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException error){
            return null;
        }
    }
}
