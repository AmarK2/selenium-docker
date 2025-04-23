package com.amark21.pages.vendorportal;

import com.amark21.pages.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractClass {

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    public void goTo(String url)
    {
        this.driver.get(url);
    }
    public void login(String username, String password)
    {
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.loginButton.click();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @java.lang.Override
    public boolean isAT() {
        this.wait.until(ExpectedConditions.visibilityOf(this.usernameInput));
        return this.usernameInput.isDisplayed();
    }
}
