package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private static ProductCategoryDaoJDBC instance = null;
    private DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductCategoryDaoJDBC getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC(dataSource);
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        // TODO to be implemented
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, department FROM category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;
            ProductCategory category = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
            category.setId(rs.getInt(1));
            return category;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Category from database", e);
        }
    }

    @Override
    public void remove(int id) {
        // TODO to be implemented
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, department FROM category ORDER BY id";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> productCategories = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = new ProductCategory(rs.getString(2),
                                                               rs.getString(3),
                                                               rs.getString(4));
                category.setId(rs.getInt(1));
                productCategories.add(category);
            }
            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Product Categories from database", e);
        }
    }
}
