package com.travelcompany.casestudy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the PriceService application.
 * This class is used to configure properties related to the PriceService.
 */
@Configuration
@ConfigurationProperties("priceservice")
public class PriceServiceConfig {

    private String dataFolder;

    /**
     * Get the data folder path.
     *
     * @return The data folder path.
     */
    public String getDataFolder() {
        return dataFolder;
    }

    /**
     * Set the data folder path.
     *
     * @param dataFolder The data folder path to set.
     */
    public void setDataFolder(String dataFolder) {
        this.dataFolder = dataFolder;
    }
}
