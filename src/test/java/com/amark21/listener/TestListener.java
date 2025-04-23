package com.amark21.listener;

import com.amark21.util.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {
    @java.lang.Override
    public void onTestFailure(ITestResult result) throws WebDriverException {
       TakesScreenshot driver= (TakesScreenshot)result.getTestContext().getAttribute(Constants.DRIVER);
       String screenshot=driver.getScreenshotAs(OutputType.BASE64);
       String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
       String htmlImage=String.format(htmlImageFormat,screenshot);
       Reporter.log(htmlImage);
    }
}
