package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {

    private static SupplierDaoJDBC instance = null;
    private DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        // TODO to be implemented
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;
            Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
            supplier.setId(rs.getInt(1));
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Supplier from database", e);
        }
    }

    @Override
    public void remove(int id) {
        // TODO to be implemented
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier ORDER BY id";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> suppliers = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString(2),
                        rs.getString(3));
                supplier.setId(rs.getInt(1));
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read Product Categories from database", e);
        }
    }
}
