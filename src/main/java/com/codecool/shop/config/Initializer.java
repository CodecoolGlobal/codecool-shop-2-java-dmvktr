package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.ProductServiceStore;
import com.codecool.shop.service.UserService;
import com.codecool.shop.service.UserServiceStore;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Arrays;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        if (AppProperties.isProductPersistenceInDatabase()) {
            DataSource dataSource = AppProperties.getDataSource();
            if (dataSource != null) {
                ProductServiceStore.initialize(dataSource);
                UserServiceStore.initialize(dataSource);
                return;
            }
        }
        ProductServiceStore.initialize();
        UserServiceStore.initialize();
        createMemoryObjects();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private void createMemoryObjects() {
        ProductService productService = ProductServiceStore.get();
        SupplierDao supplierDao = productService.getSupplierDao();
        ProductCategoryDao productCategoryDao = productService.getProductCategoryDao();
        ProductDao productDao = productService.getProductDao();

        UserService userService = UserServiceStore.get();
        UserDao userDao = userService.getUserDao();

        //setting up a new supplier
        Supplier hackerTees = new Supplier("Hacker Tees", "Hacker Tees for your Geeky Needs");
        supplierDao.add(hackerTees);
        Supplier coolStuff = new Supplier("Cool Stuff", "Your T-shirts, customized");
        supplierDao.add(coolStuff);

        //setting up a new product category
        ProductCategory tShirt = new ProductCategory("T-Shirt", "Apparel", "A t-shirt commonly shortened to tee, is awesome.");
        productCategoryDao.add(tShirt);
        ProductCategory laptopSticker = new ProductCategory("Laptop Sticker", "Stationery", "A sticker to your laptop.");
        productCategoryDao.add(laptopSticker);

        //setting up products and printing it
        productDao.add(new Product("Memory GitHub Half Sleeve Unisex T-Shirt", new BigDecimal("21.9"), "EUR", "The cloth belt is mostly decorative and a sign of wealth.", tShirt, coolStuff, "CoolCode_GitHub.jpeg", "CoolCode_GitHub_hover.jpeg"));
        productDao.add(new Product("Bug Feature Half Sleeve Unisex T-Shirt", new BigDecimal("19.9"), "EUR", "His long sleeved, silky jacket covers him to just below his waist and is buttoned up completely at the top right side.", tShirt, hackerTees, "CoolCode_Bug-Feature.jpeg", "CoolCode_Bug-Feature_hover.jpeg"));
        productDao.add(new Product("127.0.0.1 Half Sleeve Unisex T-Shirt", new BigDecimal("26.9"), "EUR", "Her sleeves are a little too long and a little narrow, their flow is broken up just above the elbow where they're divided by long, ornamental bands.", tShirt, coolStuff, "CoolCode_Theres-no-place-like.jpg", "CoolCode_Theres-no-place-like_hover.jpeg"));
        productDao.add(new Product("Commit Half Sleeve Unisex T-Shirt", new BigDecimal("23.9"), "EUR", "The sleeves of his jacket are quite narrow and reach down to his hands, they're decorated with a single thread lining and a decorative band.", tShirt, coolStuff, "CoolCode_Commit.jpeg", "CoolCode_Commit_hover.jpeg"));
        productDao.add(new Product("Do You Even Unit Test Half Sleeve Unisex T-Shirt", new BigDecimal("27.9"), "EUR", "The jacket has a deep, round neckline which reveals part of the stylish shirt worn below it and is worn with a thick cloth belt.", tShirt, hackerTees, "CoolCode_Do-you-even-unit-test.jpeg", "CoolCode_Do-you-even-unit-test_hover.jpeg"));
        productDao.add(new Product("I Write Code Half Sleeve Unisex T-Shirt", new BigDecimal("24.9"), "EUR", "His pants are simple and a little wide and reach down to his soft leather boots.", tShirt, coolStuff, "CoolCode_I-Write-Code.jpeg", "CoolCode_I-Write-Code_hover.jpeg"));
        productDao.add(new Product("Breaking Builds Half Sleeve Unisex T-Shirt", new BigDecimal("29.9"), "EUR", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tShirt, hackerTees, "CoolCode_Breaking-Builds.jpeg", "CoolCode_Breaking-Builds_hover.jpeg"));
        productDao.add(new Product("Debugging Half Sleeve Unisex T-Shirt", new BigDecimal("25.9"), "EUR", "The boots are made from a pretty uncommon leather, but are otherwise a common design.", tShirt, hackerTees, "CoolCode_Im-Sorry.jpeg", "CoolCode_Im-Sorry_hover.jpeg"));
        productDao.add(new Product("Laptop Sticker: Code Mode On", new BigDecimal("14.9"), "EUR", "A disordered field of grass is enclosed by a variety of bushes.", laptopSticker, coolStuff, "Sticker_Code-Mode.jpeg", "Sticker_Code-Mode.jpeg"));
        productDao.add(new Product("Laptop Sticker: Don't Touch My Laptop", new BigDecimal("14.9"), "EUR", "A, pudgy boulder sits in the front left, and next to it is a message carved into the stone.", laptopSticker, coolStuff, "Sticker_Dont-Touch.jpeg", "Sticker_Dont-Touch.jpeg"));
        productDao.add(new Product("Laptop Sticker: Release is Coming", new BigDecimal("13.9"), "EUR", "The bushes reach 1.8m/6ft high, but this is unusual, and perhaps unique to this garden.", laptopSticker, coolStuff, "Sticker_Release-is-Coming.jpeg", "Sticker_Release-is-Coming.jpeg"));
        productDao.add(new Product("Laptop Sticker: Stack Overflow", new BigDecimal("12.9"), "EUR", "Vines and grass are seemingly content with their positions in the garden, none trying to reach beyond, at least not yet.", laptopSticker, coolStuff, "Sticker_Stack-overflow.jpeg", "Sticker_Stack-overflow.jpeg"));

        //setting up users
        userDao.add(new User("Sanyi", "password"));
        userDao.add(new User("Béla", "password"));
    }
}