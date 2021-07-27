package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier hackerTees = new Supplier("Hacker Tees", "Hacker Tees for your Geeky Needs");
        supplierDataStore.add(hackerTees);
        Supplier coolStuff = new Supplier("Cool Stuff", "Your T-shirts, customized");
        supplierDataStore.add(coolStuff);

        //setting up a new product category
        ProductCategory tShirt = new ProductCategory("T-Shirt", "Apparel", "A t-shirt commonly shortened to tee, is awesome.");
        productCategoryDataStore.add(tShirt);
        ProductCategory sticker = new ProductCategory("Sticker", "Stationery", "A sticker to your laptop.");
        productCategoryDataStore.add(sticker);

        //setting up products and printing it
        productDataStore.add(new Product("Breaking Builds", 29.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tShirt, hackerTees, "CoolCode_Breaking-Builds.jpeg", "CoolCode_Breaking-Builds_hover.jpeg"));
        productDataStore.add(new Product("Bug Feature", 19.9f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tShirt, coolStuff, "CoolCode_Bug-Feature.jpeg", "CoolCode_Bug-Feature_hover.jpeg"));
        productDataStore.add(new Product("Commit", 23.9f, "USD", "Amazon's latest Fire HD 8 tShirt is a great value for media consumption.", tShirt, hackerTees, "CoolCode_Commit.jpeg", "CoolCode_Commit_hover.jpeg"));
        productDataStore.add(new Product("Do You Even Unit Test", 27.9f, "USD", "Yeah!", tShirt, coolStuff, "CoolCode_Do-you-even-unit-test.jpeg", "CoolCode_Do-you-even-unit-test_hover.jpeg"));
        productDataStore.add(new Product("sticker1", 27.9f, "USD", "Yeah!", sticker, coolStuff, "CoolCode_Do-you-even-unit-test.jpeg", "CoolCode_Do-you-even-unit-test_hover.jpeg"));
        productDataStore.add(new Product("sticker2", 27.9f, "USD", "Yeah!", sticker, coolStuff, "CoolCode_Do-you-even-unit-test.jpeg", "CoolCode_Do-you-even-unit-test_hover.jpeg"));
        productDataStore.add(new Product("sticker3", 27.9f, "USD", "Yeah!", sticker, coolStuff, "CoolCode_Do-you-even-unit-test.jpeg", "CoolCode_Do-you-even-unit-test_hover.jpeg"));
        productDataStore.add(new Product("sticker4", 27.9f, "USD", "Yeah!", sticker, coolStuff, "CoolCode_Do-you-even-unit-test.jpeg", "CoolCode_Do-you-even-unit-test_hover.jpeg"));

    }
}
