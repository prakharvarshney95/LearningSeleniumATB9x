package com.prakharvarshney95.ex03_Selenium;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSelenium11 {

    @Description("Open the URL")
    @Test
    public void test_Selenium11() throws Exception {

        ChromeDriver driver = new ChromeDriver();

        WebDriver driver1 = new ChromeDriver() ;
        driver1 = new FirefoxDriver();

    }

}
