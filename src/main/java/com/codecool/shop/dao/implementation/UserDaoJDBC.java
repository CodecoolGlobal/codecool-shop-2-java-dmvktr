package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBC implements UserDao {

    private DataSource dataSource;

    public UserDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public User find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, hashed_password FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;
            User user = new User(rs.getString(2), rs.getBytes(3));
            user.setId(rs.getInt(1));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read User from database", e);
        }
    }

    @Override
    public void remove(int id) {

    }

}
