package com.amark21.pages.flightreservation;

import com.amark21.pages.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractClass {

    private static final Logger log= LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css = "#flights-confirmation-section div.card-body .row:nth-child(1) div.col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(css = "#flights-confirmation-section div.card-body .row:nth-child(3) div.col:nth-child(2)")
    private WebElement priceElement;


    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @java.lang.Override
    public boolean isAT() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationElement));
        return this.flightConfirmationElement.isDisplayed();
    }

    public String getPrice()
    {
        String confirmation=this.flightConfirmationElement.getText();
        String price=this.priceElement.getText();
        log.info("confirmation id: {}", confirmation);
        log.info("price : {}",price);
        return price;
    }

}
