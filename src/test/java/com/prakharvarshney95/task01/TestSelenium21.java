package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestSelenium21 {

    @Description("Verify that free trial message is visible on iDrive 360")
    @Test
    public void verify_free_trial_message() throws Exception {

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");

        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.get("https://www.idrive360.com/enterprise/login");

        Thread.sleep(5000);

        WebElement email_input_box = driver.findElement(By.xpath("//input[@name='username']"));
        email_input_box.sendKeys("augtest_040823@idrive.com");

        WebElement password_input_box = driver.findElement(By.xpath("//input[@name='password']"));
        password_input_box.sendKeys("123456");

        WebElement sign_in_button = driver.findElement(By.xpath("//button[@id='frm-btn']"));
        sign_in_button.click();

        Thread.sleep(22000);

        WebElement trial_message = driver.findElement(By.xpath("//h5[contains(text(),'Your free trial has expired')]"));
        String actualMessage = trial_message.getText();

        String expectedMessage = "Your free trial has expired";

        Assert.assertEquals(actualMessage, expectedMessage, "Test Failed");

        System.out.println("Test Passed: Free trial message is displayed correctly.");

        driver.quit();
        System.out.println("Browser closed successfully");
    }
}