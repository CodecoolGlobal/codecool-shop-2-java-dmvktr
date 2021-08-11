package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import com.codecool.shop.service.ProductServiceStore;
import com.codecool.shop.util.DateProvider;
import com.codecool.shop.util.LogMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;
    private final ProductDao productDataStore;
    private static Logger logger = LoggerFactory.getLogger(OrderDaoMem.class);

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
            Order newOrder = addUserOrder(userID);
            logger.info("{} User {} created Order {}", DateProvider.getCurrentDateTime(), userID, newOrder.getOrderID());
            updateProductQuantityInOrder(newOrder, productDataStore.find(productID), quantityDiff);
        }
    }

    public void handleOrderUnassignedToUserID(int orderID, int productID, int quantityDiff){
        Order order = find(orderID);
        updateProductQuantityInOrder(order, productDataStore.find(productID), quantityDiff);
        logger.info("{} Visitor updated Order {}", DateProvider.getCurrentDateTime(), orderID);
    }

    public Order addUserOrder(Integer userID) {
        Order order = new Order(userID);
        order.setOrderID(data.size() + 1);
        data.add(order);
        return order;
    }

    public Order addOrder() {
        Order order = new Order();
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
    public Optional<Order> getBy(Integer userID) {
        return data.stream().filter(order -> order.getUserID().equals(userID)).findFirst();
    }

    @Override
    public void setUsersOrderItemsToNull(int userID) {
        data.stream().filter(o -> o.getUserID() == userID).findFirst().ifPresent(Order::setItemsToNull);
    }

    public void updateProductQuantityInOrder(Order order, Product product, int quantityDiff) {
        String logMessage;
        for (LineItem item : order.getItems()) {
            if (isProductInItem(product, item)) {
                logMessage = LogMessageFactory.generateLogMessage("update", order, product, quantityDiff);
                item.updateQuantity(quantityDiff);
                logger.info(logMessage);
                if (item.isQuantityZero()) {
                    order.removeItem(item);
                    logMessage = LogMessageFactory.generateLogMessage("remove", order, product, quantityDiff);
                    logger.info(logMessage);
                }
                order.refreshTotalPrice();
                order.refreshItemCount();
                return;
            }
        }
        order.getItems().add(new LineItem(product, quantityDiff));
        order.refreshTotalPrice();
        order.refreshItemCount();
        logMessage = LogMessageFactory.generateLogMessage("add", order, product, quantityDiff);
        logger.info(logMessage);

    }

    private boolean isProductInItem(Product product, LineItem item) {
        return item.getProduct().getId() == product.getId();
    }

    public void mergeOrders(Order sessionOrderWithoutUserID, Order targetOrderWithUserID){
        List<LineItem> cartContentWhenLoggedIn = targetOrderWithUserID.getItems();
        List<LineItem> cartContentWhenLoggedOut = sessionOrderWithoutUserID.getItems();
        cartContentWhenLoggedIn.addAll(cartContentWhenLoggedOut);
        // TODO SHould be OK by reference but might need setter?!
    }
}