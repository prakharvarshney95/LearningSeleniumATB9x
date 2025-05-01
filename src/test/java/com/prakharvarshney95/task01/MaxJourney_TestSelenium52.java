package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class MaxJourney_TestSelenium52 {

    @Description("Verify that YBL term journey is working")
    @Test
    public void verify_next_lesson_click() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            driver.get("https://neouat.axismaxlife.com/utp-service/api/banca/ybl/v1/processhandler?productCode=ssp&cid=uoPY5KT+POv8qwtKPWRLTw==&utm_medium=IRIS&utm_source=Whatsapp&utm_campaign=march_term");

            Thread.sleep(3000);

            // Page 1
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='5 - 7']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='No']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Salaried']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Graduate & Above']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='viewPlans' and text()='Calculate Now']"))).click();

            Thread.sleep(3000);

            // Page 2
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='viewPlans' and text()='Proceed']"))).click();

            Thread.sleep(3000);

            // Are you diabetic
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='No']"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='viewPlans' and text()='Skip']"))).click();

            Thread.sleep(3000);

            // Enter email
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("prakhar.varshney@crestechsoftware.com");

            // Enter Annual Income
            WebElement incomeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eligibilityAnnualIncome")));
            incomeField.sendKeys("794738");

            // Proceed
            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='viewPlans' and text()='Proceed']")));

            // First click
            proceedButton.click();

           // Wait for 2 seconds
            Thread.sleep(2000);

           // Second click
            proceedButton.click();

            Thread.sleep(10000);

            System.out.println("Waiting for UPI input field...");

            try {
                wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                WebElement upiField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@placeholder, 'UPI ID')]")));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", upiField);
                upiField.sendKeys("77777777777@paytm");

                System.out.println("UPI input field found.");
            } catch (TimeoutException e) {
                System.out.println("UPI input field not found within timeout. Page source:");
                System.out.println(driver.getPageSource());
                throw e;
            }

            Thread.sleep(6000);

            // Click Verify

            // Wait for 'Verify' button to become visible and enabled before clicking
            WebElement verifyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Verify']")));

           // Scroll to make sure it's in view (in case it's hidden below the fold)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyButton);

           // Add a slight wait to allow UI animation to settle if needed
            Thread.sleep(1000);

           // Now click
            verifyButton.click();
            Thread.sleep(3000);

            // Final Submit
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();

            Thread.sleep(15000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
