package com.amark21.pages.flightreservation;

import com.amark21.pages.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
public class FlightSelectionPage extends AbstractClass {
    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(id="confirm-flights")
    private WebElement confirmFlightButton;

    public FlightSelectionPage(WebDriver driver) {
        super(driver);
    }

    @java.lang.Override
    public boolean isAT() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightButton));
        return this.confirmFlightButton.isDisplayed();
    }

    public void selectFlight()
    {
        int random= ThreadLocalRandom.current().nextInt(0, departureFlightOptions.size());
        this.departureFlightOptions.get(random).click();
        this.arrivalFlightOptions.get(random).click();
    }

    public void confirmFlight()
    {
        this.confirmFlightButton.click();
    }
}
