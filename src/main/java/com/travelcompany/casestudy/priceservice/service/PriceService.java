package com.travelcompany.casestudy.priceservice.service;

import com.travelcompany.casestudy.priceservice.model.Advertiser;

import java.util.List;

/**
 * An interface for reading advertiser data.
 */
public interface PriceService {

    /**
     * Reads advertiser data and returns a list of Advertiser objects.
     *
     * @return A list of Advertiser objects containing advertiser data.
     */
    List<Advertiser> readAdvertiser();
}
