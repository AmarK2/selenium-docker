package com.amark21.tests.vendorpartal;

import com.amark21.pages.vendorportal.DashboardPage;
import com.amark21.pages.vendorportal.LoginPage;
import com.amark21.tests.AbstractTest;
import com.amark21.tests.vendorpartal.model.VendorPortalTestData;
import com.amark21.util.Config;
import com.amark21.util.Constants;
import com.amark21.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters({"testDataPath"})
    public void setPage(String testDataPath) throws Exception
    {
        this.loginPage=new LoginPage(driver);
        this.dashboardPage=new DashboardPage(driver);
        this.testData= JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest()
    {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAT());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest()
    {
        Assert.assertTrue(dashboardPage.isAT());
        Assert.assertEquals(dashboardPage.getMonthlyEarnings(),testData.monthyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarnings(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargins(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest()
    {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAT());

    }

}
