package com.prakharvarshney95.ex09_Action_Class;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSelenium28_Actions_class_SpiceJet {

   EdgeDriver driver;

   @BeforeTest
    public void openBrowser() {

       EdgeOptions edgeOptions = new EdgeOptions();
       edgeOptions.addArguments("--guest");
       driver = new EdgeDriver(edgeOptions);

   }

       @Description("Verify actions")
       @Test
       public void test_actions() throws InterruptedException {
       String URL = "https://www.spicejet.com/";
       driver.get(URL);
       driver.manage().window().maximize();

       WebElement source = driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']/div/div/input"));
       Actions actions = new Actions(driver);
       actions.moveToElement(source).click().sendKeys("BLR").build().perform();

       }

       @AfterTest
    public void closeBrowser() {
       try {
           Thread.sleep(13000);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       driver.quit();

    }
   }
