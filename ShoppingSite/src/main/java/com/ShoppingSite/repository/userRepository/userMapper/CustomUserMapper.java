package com.ShoppingSite.repository.userRepository.userMapper;

import com.ShoppingSite.model.user.CustomUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomUserMapper implements RowMapper<CustomUser> {

    @Override
    public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CustomUser(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("password"),
                rs.getInt("active"),
                rs.getString("roles"),
                rs.getString("permissions")
        );
    }
}
