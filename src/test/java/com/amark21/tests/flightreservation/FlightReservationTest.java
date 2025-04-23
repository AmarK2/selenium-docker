package com.amark21.tests.flightreservation;

import com.amark21.pages.flightreservation.*;
import com.amark21.tests.AbstractTest;
import com.amark21.tests.flightreservation.model.FlightReservationTestData;
import com.amark21.util.Config;
import com.amark21.util.Constants;
import com.amark21.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private String noOfPassenger;
    private String expectedPrice;
    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setParameter(String testDataPath) throws Exception
    {
        this.testData= JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest()
    {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAT());
        registrationPage.enterUserDetails(this.testData.firstName(), this.testData.lastName());
        registrationPage.enterUserCredentials(this.testData.email(),this.testData.password());
        registrationPage.enterStreetAddress(this.testData.street(), this.testData.city(), this.testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest()
    {
        RegistrationConfirmationPage registrationConfirmationPage=new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAT());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(),this.testData.firstName());
        registrationConfirmationPage.setGoToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest()
    {
        FlightSearchPage flightSearchPage=new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAT());
        flightSearchPage.selectPassenger(this.testData.passengersCount());
        flightSearchPage.searchFlight();
    }

    @Test(dependsOnMethods ="flightSearchTest" )
    public void flightSelectionTest()
    {
        FlightSelectionPage flightSelectionPage=new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAT());
        flightSelectionPage.selectFlight();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest()
    {
        FlightConfirmationPage flightConfirmationPage=new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAT());
        Assert.assertEquals(flightConfirmationPage.getPrice(),this.testData.expectedPrice());
    }

}
