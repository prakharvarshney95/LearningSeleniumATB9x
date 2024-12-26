package com.prakharvarshney95.ex03_Selenium;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSelenium09 {

    @Description("Open the URL")
    @Test
    public void test_Selenium09(){

        WebDriver driver = new EdgeDriver();
        driver.get("http://google.com");

        Assert.assertEquals(driver.getTitle(),"Google");
    }

}
