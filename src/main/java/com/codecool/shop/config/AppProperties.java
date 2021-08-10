package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    public static final String SERVER;
    public static final String DATABASE;
    public static final String USER;
    public static final String PASSWORD;
    public static final String PRODUCT_PERSISTENCE;
    public static final String ORDER_PERSISTENCE;

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "connection.properties";
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SERVER = p.getProperty("server", null);
        DATABASE = p.getProperty("database", null);
        USER = p.getProperty("user", null);
        PASSWORD = p.getProperty("password", null);
        PRODUCT_PERSISTENCE = p.getProperty("product_persistence", null);
        ORDER_PERSISTENCE = p.getProperty("memory", null);
    }

    public static DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(SERVER);
        dataSource.setDatabaseName(DATABASE);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        try {
            System.out.println("Trying to connect...");
            dataSource.getConnection().close();
            System.out.println("Connection OK");
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isProductPersistenceInMemory() {
        return PRODUCT_PERSISTENCE.equals("memory");
    }

    public static boolean isProductPersistenceInDatabase() {
        return PRODUCT_PERSISTENCE.equals("database");
    }
}
