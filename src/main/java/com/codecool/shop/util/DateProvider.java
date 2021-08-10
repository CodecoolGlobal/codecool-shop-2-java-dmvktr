package com.codecool.shop.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateProvider {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return "[" + now.format(formatter) + "]";
    }
}
