package com.vogella.junit.first;

import blackjack.models.OpenWeatherApi;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

/**
 * Test OpenWeateherApi class
 * @author Tomas Alander
 * @version 2022-03-07
 */

class JUnitTestOWApi {
    private OpenWeatherApi openWeather;
    private String weatherError = "Coordinates dont exist";

    @BeforeAll
    public static void startTest() {
      System.out.println("Start OpenWeateherApi test");
    }
    @BeforeEach
    void setUp() {
        openWeather = new OpenWeatherApi();
    }
    @Test
    @DisplayName("Test OpenWeather getLocalWeather")
    public void testGetLocalWeather(){
        assertEquals(weatherError,openWeather.getLocalWeather(-100,-115.172813));
        assertEquals(weatherError,openWeather.getLocalWeather(91,-115.172813));
        assertEquals(weatherError,openWeather.getLocalWeather(50,-180.172813));
        assertEquals(weatherError,openWeather.getLocalWeather(50,180.172813));
        Assert.assertNotEquals(weatherError,openWeather.getLocalWeather(36.114647,-115.172813));
    }

    @Test
    @DisplayName("Test OpenWeather getLasVegasLatitude and getLasVegasLongitude")
    public void testGetLocalPosition(){
        Assertions.assertEquals(36.114647,openWeather.getLasVegasLatitude());
        Assertions.assertEquals(-115.172813,openWeather.getLasVegasLongitude());
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("All OpenWeateherApi tests are completed");
    }
}