package com.amark21.pages.flightreservation;

import com.amark21.pages.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractClass {
    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightSearchButton;

    @FindBy(css="#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public  RegistrationConfirmationPage(WebDriver driver)
    {
        super(driver);
    }

    @java.lang.Override
    public boolean isAT() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();

    }

    public String getFirstName()
    {
        return this.firstNameElement.getText();
    }


    public void setGoToFlightSearch()
    {
        this.goToFlightSearchButton.click();
    }


}
