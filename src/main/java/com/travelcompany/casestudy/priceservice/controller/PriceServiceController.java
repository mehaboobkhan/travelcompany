package com.travelcompany.casestudy.priceservice.controller;

import com.travelcompany.casestudy.exception.AccommodationNotFoundException;
import com.travelcompany.casestudy.exception.InvalidAccommodationIdException;
import com.travelcompany.casestudy.priceservice.model.Accommodation;
import com.travelcompany.casestudy.priceservice.model.Advertiser;
import com.travelcompany.casestudy.priceservice.model.Price;
import com.travelcompany.casestudy.priceservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PriceServiceController {

  @Autowired
  @Qualifier("json")
  private PriceService priceServiceJson;

  @Autowired
  @Qualifier("yaml")
  private PriceService priceServiceYaml;

  private static final Logger logger = LoggerFactory.getLogger(PriceServiceController.class);

  // Controller responsible for handling price-related requests

  // Implementation of this API was not clear if partnerId will be given in the request.
  // I implemented 2 different APIs, one which accepts accommodationId and other which accepts both partnerId and accommodationId

  // Get prices for a specific accommodation ID
  @GetMapping("/prices/{accommodationId}")
  public ResponseEntity<List<Price>> getPrices(@PathVariable(name = "accommodationId") int accommodationId) {
    logger.info("Get Price API Triggered with accommodationId: {}", accommodationId);
    validateAccommodationId(accommodationId);
    List<Accommodation> accommodations = findAccommodations(-1, accommodationId, null);
    return ResponseEntity.status(HttpStatus.OK).body(createResponse(accommodations));
  }

  // Get prices for a specific partner ID and accommodation ID
  @GetMapping("/prices/{partnerId}/{accommodationId}")
  public ResponseEntity<List<Price>>  getPricesForPartnerId(@PathVariable(name = "partnerId") int partnerId, @PathVariable(name = "accommodationId") int accommodationId) {
    logger.info("Get Price API Triggered with partnerId: {} and accommodationId: {}", partnerId, accommodationId);
    validateAccommodationId(accommodationId);
    List<Accommodation> accommodations = findAccommodations(partnerId, accommodationId, null);
    return ResponseEntity.status(HttpStatus.OK).body(createResponse(accommodations));
  }

  @GetMapping("/prices/{accommodationId}/filter/{currencyType}")
  public ResponseEntity<List<Price>>  getPriceWithFilter(@PathVariable(name = "accommodationId") int accommodationId, @PathVariable(name = "currencyType") String currencyType ) {
    logger.info("Get Price API Triggered with and accommodationId: {} and currencyType: {}", accommodationId, currencyType);
    validateAccommodationId(accommodationId);
    List<Accommodation> accommodations = findAccommodations(-1, accommodationId, currencyType);
    return ResponseEntity.status(HttpStatus.OK).body(createResponse(accommodations));
  }
  // Validate that the accommodation ID is valid (not negative)
  private void validateAccommodationId(int accommodationId) {
    if (accommodationId < 0) {
      logger.error("Invalid accommodation ID: {}", accommodationId);
      throw new InvalidAccommodationIdException("Invalid accommodation ID");
    }
  }

  // Find accommodations based on partner ID and accommodation ID
  private List<Accommodation> findAccommodations(int partnerId, int accommodationId, String currencyType) {
    logger.info("Searching for accommodations with ID: {}", accommodationId);
    List<Advertiser> advertisers = new ArrayList<Advertiser>();
    advertisers.addAll(priceServiceJson.readAdvertiser());
    advertisers.addAll(priceServiceYaml.readAdvertiser());
    List<Accommodation> foundAccommodations = new ArrayList<>();

    for (Advertiser advertiser : advertisers) {
      if (partnerId == -1 || advertiser.getId() == partnerId) {
        advertiser.getAccommodation()
                .stream()
                .filter(acc -> acc.getId() == accommodationId)
                .findFirst()
                .ifPresent(foundAccommodations::add);
        List<Price> result = new ArrayList<Price>();
        if(currencyType!=null){
          List<Accommodation> results = new ArrayList<Accommodation>();
          for(Accommodation acc: foundAccommodations){
            for(Price price: acc.getPrices()) {
              if(price.getCurrency().equalsIgnoreCase(currencyType)){
                results.add(acc);
              }
            }
          }
          foundAccommodations = results;
        }
      }
    }

    if (foundAccommodations.isEmpty()) {
      logger.warn("No accommodations found");
      throw new AccommodationNotFoundException("No accommodations found");
    }

    return foundAccommodations;
  }

  // Create a response containing prices for the given accommodations
  private List<Price> createResponse(List<Accommodation> accommodations) {
    List<Price> allPrices = accommodations.stream()
            .flatMap(accommodation -> accommodation.getPrices().stream())
            .collect(Collectors.toList());

    if (allPrices.isEmpty()) {
      logger.info("No prices found for accommodations");
      return Collections.emptyList();
    }

    return allPrices;
  }
}
