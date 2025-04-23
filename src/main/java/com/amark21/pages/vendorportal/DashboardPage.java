package com.amark21.pages.vendorportal;

import com.amark21.pages.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractClass {

    private static final Logger log= LoggerFactory.getLogger(DashboardPage.class);
    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;
    @FindBy(id="annual-earning")
    private WebElement annualEarningElement;
    @FindBy(id="profit-margin")
    private WebElement profitMarginElement;
    @FindBy(id="available-inventory")
    private WebElement availableInventoryElement;
    @FindBy(css="#dataTable_filter input")
    private WebElement searchInput;
    @FindBy(id="dataTable_info")
    private WebElement searchResultCountElement;
    @FindBy(css="img.img-profile")
    private WebElement userProfilePictureElement;
    @FindBy(linkText="Logout")
    private WebElement logoutLink;
    @FindBy(css="#logoutModal a")
    private WebElement modalLogoutButton;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getMonthlyEarnings()
    {
        String monthlyEarnings=this.monthlyEarningElement.getText();
        log.info("Monthly Earnings : {}",monthlyEarnings);
        return monthlyEarnings;
    }

    public String getAnnualEarnings()
    {
        String annualEarnings=this.annualEarningElement.getText();
        log.info("Annual Earnings : {}",annualEarnings);
        return annualEarnings;
    }
    public String getProfitMargins()
    {
        String profitMargins=this.profitMarginElement.getText();
        log.info("Profit Margins : {}",profitMargins);
        return profitMargins;
    }
    public String getAvailableInventory()
    {
        String availableInventory=this.availableInventoryElement.getText();
        log.info("Available Inventory: {}",availableInventory);
        return availableInventory;
    }

    public void searchOrderHistoryBy(String keyword)
    {
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultCount()
    {
        String[] resultText=this.searchResultCountElement.getText().split(" ");
        int count= Integer.parseInt(resultText[5]);
        log.info("Result count : {}",count);
        return count;
    }
    public void logout()
    {
        this.userProfilePictureElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }

    @java.lang.Override
    public boolean isAT() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningElement));
        return this.monthlyEarningElement.isDisplayed();
    }
}
