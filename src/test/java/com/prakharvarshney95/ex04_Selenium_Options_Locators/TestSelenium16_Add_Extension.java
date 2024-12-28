package com.prakharvarshney95.ex04_Selenium_Options_Locators;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import java.io.File;

public class TestSelenium16_Add_Extension {

    @Description("Options Class")
    @Test
    public void test_Selenium15() throws Exception {

        // EdgeOptions, ChromeOptions, FirefoxOptions, SafariOptions
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--window-size=1920,1080");
        edgeOptions.addExtensions(new File("src/test/java/com/prakharvarshney95/ex04_Selenium_Options_Locators/AdBlocker.crx"));


//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("188.255.245.205:1080");
//        edgeOptions.setCapability("proxy", proxy);



        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://youtube.com");

        Thread.sleep(35000);
        driver.quit();
        
    }

}
