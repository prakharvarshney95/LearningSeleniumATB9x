package com.prakharvarshney95.ex03_Selenium;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class TestSelenium08 {

    @Description("Open the URL")
    @Test
    public void test_Selenium08(){

        WebDriver driver = new EdgeDriver();
        driver.get("http://google.com");

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getPageSource());

        driver.manage().window().maximize();
        driver.manage().window().minimize();



    }

}
