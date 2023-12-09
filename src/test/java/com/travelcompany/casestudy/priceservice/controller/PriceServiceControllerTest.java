package com.travelcompany.casestudy.priceservice.controller;

import com.travelcompany.casestudy.exception.AccommodationNotFoundException;
import com.travelcompany.casestudy.exception.InvalidAccommodationIdException;
import com.travelcompany.casestudy.priceservice.model.Accommodation;
import com.travelcompany.casestudy.priceservice.model.Advertiser;
import com.travelcompany.casestudy.priceservice.model.Price;
import com.travelcompany.casestudy.priceservice.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PriceServiceControllerTest {

    @InjectMocks
    private PriceServiceController controller;

    @Mock
    @Qualifier("json")
    private PriceService priceServiceJson;

    @Mock
    @Qualifier("yaml")
    private PriceService priceServiceYaml;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPrices_WithValidAccommodationIdFromJson_ShouldReturnPrices() {
        // Arrange
        int accommodationId = 1;
        List<Advertiser> advertisers = createAdvertisersWithAccommodation(accommodationId);
        when(priceServiceJson.readAdvertiser()).thenReturn(advertisers);

        // Act
        ResponseEntity<List<Price>> responseEntity = controller.getPrices(accommodationId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void testGetPrices_WithValidAccommodationIdFromYaml_ShouldReturnPrices() {
        // Arrange
        int accommodationId = 1;
        List<Advertiser> advertisers = createAdvertisersWithAccommodation(accommodationId);
        when(priceServiceYaml.readAdvertiser()).thenReturn(advertisers);

        // Act
        ResponseEntity<List<Price>> responseEntity = controller.getPrices(accommodationId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }

    @Test
    public void testGetPrices_WithInvalidAccommodationId_ShouldThrowException() {
        // Arrange
        int invalidAccommodationId = -1;

        // Act and Assert
        assertThrows(InvalidAccommodationIdException.class, () -> {
            controller.getPrices(invalidAccommodationId);
        });
    }

    @Test
    public void testGetPrices_WithNoAccommodationsFound_ShouldThrowException() {
        // Arrange
        int accommodationId = 1;
        when(priceServiceJson.readAdvertiser()).thenReturn(Collections.emptyList());
        when(priceServiceYaml.readAdvertiser()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(AccommodationNotFoundException.class, () -> {
            controller.getPrices(accommodationId);
        });
    }

    @Test
    public void testGetPrices_WithNoPricesFound_ShouldReturnEmptyList() {
        // Arrange
        int accommodationId = 1;
        List<Advertiser> advertisers = createAdvertisersWithAccommodation(accommodationId);
        advertisers.get(0).getAccommodation().get(0).setPrices(Collections.emptyList());
        when(priceServiceJson.readAdvertiser()).thenReturn(advertisers);
        when(priceServiceYaml.readAdvertiser()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Price>> responseEntity = controller.getPrices(accommodationId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());
    }

    private List<Advertiser> createAdvertisersWithAccommodation(int accommodationId) {
        List<Advertiser> advertisers = new ArrayList<>();
        Advertiser advertiser = new Advertiser();
        Accommodation accommodation = new Accommodation();
        Price price = new Price();
        accommodation.setId(accommodationId);
        accommodation.getPrices().add(price);
        advertiser.getAccommodation().add(accommodation);
        advertisers.add(advertiser);
        return advertisers;
    }
}
