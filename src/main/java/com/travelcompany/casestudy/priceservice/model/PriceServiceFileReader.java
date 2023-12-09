package com.travelcompany.casestudy.priceservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads advertiser data from files in a specified data folder.
 */
public class PriceServiceFileReader {
    private final ObjectMapper objectMapper;
    private static final Logger logger = Logger.getLogger(PriceServiceFileReader.class.getName());

    public PriceServiceFileReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Reads advertiser data from files in the given data folder with a specific file extension.
     *
     * @param dataFolder The folder where data files are located.
     * @param fileExtension The extension of the data files to be read.
     * @return A list of Advertiser objects.
     */
    public List<Advertiser> readAdvertiser(String dataFolder, String fileExtension) {
        return findFiles(dataFolder, fileExtension)
                .stream()
                .map(this::readFile)
                .collect(Collectors.toList());
    }

    /**
     * Reads advertiser data from a file.
     *
     * @param file The data file to be read.
     * @return An Advertiser object.
     */
    private Advertiser readFile(File file) {
        try {
            return objectMapper.readValue(file, Advertiser.class);
        } catch (IOException e) {
            handlePriceServiceException(e);
            return null;
        }
    }

    /**
     * Handles exceptions that may occur while reading advertiser data.
     *
     * @param e The exception that was caught.
     */
    private void handlePriceServiceException(IOException e) {
        logger.log(Level.SEVERE, "An error occurred while reading advertiser data", e);
    }

    /**
     * Finds data files in the given data folder with a specific file extension.
     *
     * @param dataFolder The folder where data files are located.
     * @param fileExtension The extension of the data files to be found.
     * @return A list of File objects representing the data files.
     */
    private List<File> findFiles(String dataFolder, String fileExtension) {
        File[] files = new File(dataFolder)
                .listFiles((dir, name) -> name.toLowerCase().endsWith(fileExtension));
        return Stream.of(files).collect(Collectors.toList());
    }
}
