package com.prakharvarshney95.ex09_Action_Class;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestSelenium29_Actions_class_makemytrip {

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
       String URL = "https://www.makemytrip.com/";
       driver.get(URL);
       driver.manage().window().maximize();

           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy=\"closeModal\"]")));

           driver.findElement(By.xpath("//span[@data-cy=\"closeModal\"]")).click();

           WebElement fromCity = driver.findElement(By.id("fromCity"));

           Actions actions = new Actions(driver);
           actions.moveToElement(fromCity).click().sendKeys("del").build().perform();

           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }

//           actions.moveToElement(fromCity).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).perform();

           List<WebElement> list_auto_complete = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));

           for (WebElement e : list_auto_complete) {
               if(e.getText().contains("New Delhi")) {
                   e.click();
                   break;
               }
           }

       }

       @AfterTest
    public void closeBrowser() {
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       driver.quit();

    }
   }
