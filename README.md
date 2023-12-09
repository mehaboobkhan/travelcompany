# <u>TRAVELCOMPANY</u> #


# PRICESERVICE CASE STUDY #

## Overview

This is a Spring boot application for Travelcompany Price Service API.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK)**: Download and install a compatible JDK version.
- **Maven or Gradle**: Install the build tool you prefer.

## Getting Started

1. **Build the Project:**

   ```bash
   mvn clean install

2. **Run the Application:**

   ```bash
   mvn spring-boot:run

3. **Access the Application::**

   Once the application is running, you can access it in a web browser or using a tool like curl. By default, a Spring Boot application starts on port 8080, but you can configure the port in the application.properties
   * http://localhost:8080


4. **API Endpoint Details:**

```
| Endpoint                                | Method   | Description                                                         |
| --------------------------------------- | -------- | ------------------------------------------------------------------- |
| `/prices/{accommodationId}`             | GET      | Get price for a given accommodation from all the available partners.|
| `/prices/{partnerId}/{accommodationId}` | GET      | Get price for a given accommodation and partners.                   |
```
## For Developers:

1. **Project structure:**
```
project-root/
|
├── logs/
│   └── application.log
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.travelcompany.casestudy/
│   │   │   │   ├── config/
│   │   │   │   │   └── PriceServiceConfig.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── AccommodationNotFoundException.java
│   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   └── InvalidAccommodationIdException.java
│   │   │   │   ├── priceservice/
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   └── PriceServiceController.java
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── Accommodation.java
│   │   │   │   │   │   ├── Advertiser.java
│   │   │   │   │   │   ├── Price.java
│   │   │   │   │   │   └── PriceServiceFileReader.java
│   │   │   │   │   ├── service/
│   │   │   │   │   │   └── PriceService.java
│   │   │   │   │   └── serviceImpl/
│   │   │   │   │       ├── PriceServiceJsonImpl.java
│   │   │   │   │       └── PriceServiceYamlImpl.java
│   │   │   │   └── CasestudyApplication
│   │   └── resources/
│   │       ├── static/
│   │       │   └── prices/
│   │       │       ├── advertiser_100.yaml
│   │       │       └── advertiser_200.json
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com.travelcompany.casestudy/
│               ├── priceservice/
│               │   └── controller/
│               │       └── PriceServiceControllerTest
│               └── CasestudyApplicationTests
├── target/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── CHALLENGE.md
├── README.md
└── HELP.md
```

**2. Integrating additional partners.**

* To make the solution extensible for more partners, you can create additional YAML or JSON files and restart the application.
* If you want to add other source of data like csv files, implement PriceService for the same and make necessary changes in the controller.

**3. How could a partner with a potentially slow REST interface be integrated?**

* To integrate a partner with a potentially slow REST interface, you can implement asynchronous processing. When calling the partner's API, use asynchronous libraries like CompletableFuture in Java. This will prevent the service from getting blocked while waiting for a slow partner to respond.
* Use a cache to store frequently accessed data to reduce file read operations.
* Implementing timeout mechanisms.
* Use a load balancer to distribute traffic across multiple instances of your Spring Boot application.

**4. How could your solution scale for multiple thousand requests per second?**

* Use a load balancer to distribute traffic across multiple instances of your Spring Boot application.
* Use a cache to store frequently accessed data to reduce file read operations.
* Optimize the code for performance, e.g., use multithreading or reactive programming.
* Implement proper error handling and retries for partner services.
* Use connection pooling to efficiently manage connections to partner services.

## **Closing:** ##

####  _Thanks for joining the fun and may your code always compile on the first try! 🚀_ ####