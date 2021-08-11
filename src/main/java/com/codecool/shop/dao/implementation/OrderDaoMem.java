package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.AppProperties;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import com.codecool.shop.service.ProductServiceStore;
import com.codecool.shop.util.DateProvider;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;
    private final ProductDao productDataStore;
    private final Logger logger = LoggerFactory.getLogger(OrderDaoMem.class);

    public OrderDaoMem() {
        productDataStore = ProductServiceStore.get().getProductDao();
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void handleOrderUpdate(int userID, int productID, int quantityDiff) {
        Optional<Order> order = getBy(userID);
        if (order.isPresent()) {
            updateProductQuantityInOrder(order.get(), productDataStore.find(productID), quantityDiff);
        }
        else {
            Order newOrder = addOrder(userID);
            logger.info("User {} created Order {}", userID, newOrder.getOrderID());
            updateProductQuantityInOrder(newOrder, productDataStore.find(productID), quantityDiff);
        }
    }

    private Order addOrder(int userID) {
        Order order = new Order(userID);
        order.setOrderID(data.size() + 1);
        data.add(order);
        return order;
    }

    @Override
    public Order find(int orderID) {
        return data.stream().filter(order -> order.getOrderID() == orderID).findFirst().orElse(null);
    }

    @Override
    public void remove(int orderID) {
        Order order = find(orderID);
        if (order != null) {
            data.remove(order);
        }
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Optional<Order> getBy(int userID) {
        return data.stream().filter(order -> order.getUserID() == userID).findFirst();
    }

    @Override
    public void setUsersOrderItemsToNull(int userID) {
        data.stream().filter(o -> o.getUserID() == userID).findFirst().ifPresent(Order::setItemsToNull);
    }

    public void updateProductQuantityInOrder(Order order, Product product, int quantityDiff) {
        for (LineItem item : order.getItems()) {
            if (isProductInItem(product, item)) {
                item.updateQuantity(quantityDiff);
                logger.info("User {} updated Product {} quantity by {} in Order {}", order.getUserID(), product.getId(), quantityDiff, order.getOrderID());

                if (item.isQuantityZero()) {
                    order.removeItem(item);
                    logger.info("User {} removed Product {} from Order {}.", order.getUserID(), product.getId(), order.getUserID());
                }
                order.refreshTotalPrice();
                order.refreshItemCount();
                return;
            }
        }
        order.getItems().add(new LineItem(product, quantityDiff));
        order.refreshTotalPrice();
        order.refreshItemCount();
        logger.info("User {} added Product {} to Order {}", order.getUserID(), product.getId(), order.getOrderID());

    }

    private boolean isProductInItem(Product product, LineItem item) {
        return item.getProduct().getId() == product.getId();
    }
}