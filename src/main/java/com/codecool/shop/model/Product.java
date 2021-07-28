package com.codecool.shop.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Currency;

public class Product extends BaseModel implements Serializable {

    private float defaultPrice;
    private Currency defaultCurrency;
    private transient ProductCategory productCategory;
    private transient Supplier supplier;
    private String imagePath;
    private String hoverImagePath;
    protected static final String baseImagePath = "/static/img/product-img/";


    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier, String imagePath, String hoverImagePath) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        this.setImagePath(imagePath);
        this.setHoverImagePath(hoverImagePath);
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setHoverImagePath(String hoverImagePath) {
        this.hoverImagePath = hoverImagePath;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getHoverImagePath() {
        return hoverImagePath;
    }

    public static String getBaseImagePath() {
        return baseImagePath;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, ",
//                        "defaultCurrency: %4$s, " +
//                        "productCategory: %5$s, " +
//                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice);
//                this.defaultCurrency.toString(),
//                this.productCategory.getName(),
//                this.supplier.getName());
    }
}
