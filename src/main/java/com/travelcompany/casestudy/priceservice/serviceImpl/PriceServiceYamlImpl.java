package com.travelcompany.casestudy.priceservice.serviceImpl;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.travelcompany.casestudy.priceservice.model.Advertiser;
import com.travelcompany.casestudy.priceservice.service.PriceService;
import com.travelcompany.casestudy.config.PriceServiceConfig;
import com.travelcompany.casestudy.priceservice.model.PriceServiceFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Implementation of the PriceService interface for reading advertiser data from YAML files.
 */
@Service
@Qualifier("yaml")
public class PriceServiceYamlImpl implements PriceService {
    @Autowired
    private final PriceServiceConfig config;
    private final PriceServiceFileReader fileReader;

    @Autowired
    public PriceServiceYamlImpl(PriceServiceConfig config) {
        this.config = config;
        this.fileReader = new PriceServiceFileReader(new ObjectMapper(new YAMLFactory()));
    }

    /**
     * Reads advertiser data from YAML files and returns a list of Advertiser objects.
     *
     * @return A list of Advertiser objects containing advertiser data from YAML files.
     */
    @Override
    public List<Advertiser> readAdvertiser() {
        return fileReader.readAdvertiser(config.getDataFolder(), ".yaml");
    }
}
