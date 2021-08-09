package com.codecool.shop.model;

import java.util.List;

public class CheckoutDetails {

    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String country;
    private String streetAddress;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private String comment;

    public CheckoutDetails(List<String> checkoutDetailsParams) {
        this.firstName = checkoutDetailsParams.get(0);
        this.lastName = checkoutDetailsParams.get(1);
        this.company = checkoutDetailsParams.get(2);
        this.email = checkoutDetailsParams.get(3);
        this.country = checkoutDetailsParams.get(4);
        this.streetAddress = checkoutDetailsParams.get(5);
        this.city = checkoutDetailsParams.get(6);
        this.zipCode = checkoutDetailsParams.get(7);
        this.phoneNumber = checkoutDetailsParams.get(8);
        this.comment = checkoutDetailsParams.get(9);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getComment() {
        return comment;
    }
}