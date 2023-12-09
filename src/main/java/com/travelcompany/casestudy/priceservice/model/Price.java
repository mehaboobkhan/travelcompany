package com.travelcompany.casestudy.priceservice.model;

/**
 * Represents an Price.
 */
public class Price {
    private String currency; // The currency of the price
    private String price;    // The price value

    // Default constructor
    public Price() {
    }

    // Parameterized constructor to set currency and price
    public Price(String currency, String price) {
        this.currency = currency;
        this.price = price;
    }

    // Getter method for retrieving the currency value
    public String getCurrency() {
        return currency;
    }

    // Setter method to set the currency value
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // Getter method for retrieving the price value
    public String getPrice() {
        return price;
    }

    // Setter method to set the price value
    public void setPrice(String price) {
        this.price = price;
    }

}
