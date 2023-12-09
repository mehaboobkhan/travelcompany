package com.travelcompany.casestudy.priceservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Accommodation.
 */
public class Accommodation {
    private int id;
    private List<Price> prices = new ArrayList<Price>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
