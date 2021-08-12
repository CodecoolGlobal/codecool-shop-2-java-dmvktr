package com.codecool.shop.util;

public class LogMessageFactory {
    public static String generateLogMessage(LogActionType operation, int orderID, int productID, int quantityDiff, int userID){
        switch (operation){
            case REMOVE:
                return isUserIDMissing(userID)?
                    String.format("Visitor removed product %d from Order %d", productID, orderID):
                    String.format("User %d removed Product %d from Order %d", userID,productID, orderID);
            case UPDATE:
                return isUserIDMissing(userID)?
                    String.format("Visitor updated product %d quantity by %d in Order %d", productID, quantityDiff, orderID):
                    String.format("User %d updated Product %d quantity by %d in Order %d", userID, productID, quantityDiff, orderID);
            case CREATE:
                return isUserIDMissing(userID)?
                    String.format("Visitor created Order %d", orderID):
                    String.format("User %d created Order %d", orderID, productID);
            default:
                return isUserIDMissing(userID)?
                    String.format("Visitor added product %d to Order %d", productID, orderID):
                    String.format("User %d added Product %d to Order %d", userID, productID, orderID);
        }
    }

    private static boolean isUserIDMissing(int userID){
        return userID < 0;
    }
}
