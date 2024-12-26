package com.prakharvarshney95.ex03_Selenium;

import io.qameta.allure.Description;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class TestSelenium03 {

    @Description("Open the App.vwo.com and Get the title")
    @Test
    public void test_Selenium03(){

        EdgeDriver driver = new EdgeDriver();
        driver.get("https://google.com");
        System.out.println(driver.getTitle());

    }

}
