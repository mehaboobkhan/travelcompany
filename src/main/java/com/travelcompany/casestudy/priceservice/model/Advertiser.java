package com.travelcompany.casestudy.priceservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Advertiser.
 */
public class Advertiser {
    private String name;
    private int id;
    private List<Accommodation> accommodation = new ArrayList<Accommodation>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Accommodation> getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(List<Accommodation> accommodation) {
        this.accommodation = accommodation;
    }
}
