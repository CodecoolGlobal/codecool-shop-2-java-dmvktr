package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private static ProductDaoJDBC instance = null;
    private DataSource dataSource;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJDBC(DataSource dataSource) {
        productCategoryDao = ProductCategoryDaoJDBC.getInstance(dataSource);
        supplierDao = SupplierDaoJDBC.getInstance(dataSource);
        this.dataSource = dataSource;
    }

    public static ProductDaoJDBC getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProductDaoJDBC(dataSource);
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
        // TODO to be implemented
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id, image_path, hover_image_path FROM product WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;
            Product product = new Product(
                    rs.getString(2),
                    rs.getBigDecimal(3),
                    rs.getString(4),
                    rs.getString(5),
                    productCategoryDao.find(rs.getInt(6)),
                    supplierDao.find(rs.getInt(7)),
                    rs.getString(8),
                    rs.getString(9));
            product.setId(rs.getInt(1));
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Product from database", e);
        }
    }

    @Override
    public void remove(int id) {
        // TODO to be implemented
    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id, image_path, hover_image_path FROM product ORDER BY id";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getString(5),
                        productCategoryDao.find(rs.getInt(6)),
                        supplierDao.find(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9));
                product.setId(rs.getInt(1));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Products from database", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id, image_path, hover_image_path FROM product WHERE supplier_id = ? ORDER BY id";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getString(5),
                        productCategoryDao.find(rs.getInt(6)),
                        supplierDao.find(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9));
                product.setId(rs.getInt(1));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Products from database", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, default_currency, description, category_id, supplier_id, image_path, hover_image_path FROM product WHERE category_id = ? ORDER BY id";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString(2),
                        rs.getBigDecimal(3),
                        rs.getString(4),
                        rs.getString(5),
                        productCategoryDao.find(rs.getInt(6)),
                        supplierDao.find(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9));
                product.setId(rs.getInt(1));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Products from database", e);
        }
    }
}
