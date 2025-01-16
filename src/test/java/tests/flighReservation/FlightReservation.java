package tests.flighReservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.flightReservation.*;
import tests.AbstractTest;
import tests.DashBoardTest.model.TestData;
import tests.flighReservation.model.FlightData;
import utilities.Config;
import utilities.Constants;
import utilities.jsonUtil;

import java.time.Duration;

public class FlightReservation extends AbstractTest
{
    private FlightData flightData;
    @BeforeTest
    @Parameters("testDataPath")
    public  void setPageObject(String testDataPath) throws  Exception
    {
       this.flightData = jsonUtil.getResource(testDataPath, FlightData.class);
    }

    @Test
    public void userRegistrationTest() throws Exception
    {
        System.out.println(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.getKey(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails(flightData.firstName(), flightData.lastName());
        registrationPage.enterUserCredentials(flightData.email(), flightData.password());
        registrationPage.enterAddress(flightData.city(), flightData.zip(), flightData.street() );
        registrationPage.clickRegister();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmstionTest()
    {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(flightData.firstName(),flightConfirmationPage.getName());
        registrationConfirmationPage.clickFlightSearchBtn();
    }

    @Test(dependsOnMethods = "registrationConfirmstionTest")
    public void flightSearchPageTest()throws Exception
    {
        FlightsSearch flightsSearch = new FlightsSearch(driver);
        Assert.assertTrue(flightsSearch.isAt());
        flightsSearch.selectPassengers(flightData.passengersCount());
        flightsSearch.flightSearchBTn();
    }

    @Test(dependsOnMethods = "flightSearchPageTest")
    public void flightSelectionPageTest()
    {
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionPageTest")
    public void flightConfirmationPageTest()
    {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),flightData.expectedPrice());
    }
}
