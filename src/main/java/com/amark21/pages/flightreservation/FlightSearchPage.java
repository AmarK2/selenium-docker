package com.amark21.pages.flightreservation;

import com.amark21.pages.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractClass {
    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="passengers")
    private WebElement passenger;

    @FindBy(id="search-flights")
    private WebElement searchFlightButton;

    @java.lang.Override
    public boolean isAT() {
        this.wait.until(ExpectedConditions.visibilityOf(searchFlightButton));
        return this.searchFlightButton.isDisplayed();
    }

    public void selectPassenger(String noOfPassenger)
    {
        Select selectPassenger=new Select(passenger);
        selectPassenger.selectByValue(noOfPassenger);
    }
    public void searchFlight()
    {
        this.searchFlightButton.click();
    }

}
