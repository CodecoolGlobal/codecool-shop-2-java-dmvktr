package com.codecool.shop.util;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public class LogMessageFactory {
    public static String generateLogMessage(LogActionType operation, Order order, Product product, int quantityDiff){
        switch (operation){
            case REMOVE:
                return isUserIDMissing(order)?
                    String.format("%s Visitor removed product %d from Order %d", DateProvider.getCurrentDateTime(), product.getId(), order.getOrderID()):
                    String.format("%s User %d removed Product %d from Order %d", DateProvider.getCurrentDateTime(), order.getUserID(),product.getId(), order.getUserID());
            case UPDATE:
                return isUserIDMissing(order)?
                    String.format("%s Visitor updated product %d quantity by %d in Order %d", DateProvider.getCurrentDateTime(), product.getId(), quantityDiff, order.getOrderID()):
                    String.format("%s User %d updated Product %d quantity by %d in Order %d", DateProvider.getCurrentDateTime(), order.getUserID(), product.getId(), quantityDiff, order.getOrderID());
            default:
                return isUserIDMissing(order)?
                    String.format("%s Visitor added product %d to Order %d", DateProvider.getCurrentDateTime(), product.getId(), order.getOrderID()):
                    String.format("%s User %d added Product %d to Order %d", DateProvider.getCurrentDateTime(), order.getUserID(), product.getId(), order.getOrderID());
        }
    }

    private static boolean isUserIDMissing(Order order){
        return order.getUserID() == null;
    }
}
