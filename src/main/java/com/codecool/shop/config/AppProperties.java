package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.util.DateProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.PropertyConfigurator;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class AppProperties {

    private static final String SERVER;
    private static final String DATABASE;
    private static final String USER;
    private static final String PASSWORD;
    private static final String PRODUCT_PERSISTENCE;
    private static final String ORDER_PERSISTENCE;
    private static Logger logger = LoggerFactory.getLogger(AppProperties.class);

    static {

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "connection.properties";
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            logger.warn("'connection.properties' not found, loading products from memory");
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
            dataSource.getConnection().close();
            logger.info("Database connection successful, loading products from database");
            return dataSource;
        } catch (SQLException e) {
            logger.warn("Database connection failed, loading products from memory");
            return null;
        }
    }

    public static boolean isProductPersistenceInDatabase() {
        return PRODUCT_PERSISTENCE != null
                && PRODUCT_PERSISTENCE.equals("database");
    }
}
