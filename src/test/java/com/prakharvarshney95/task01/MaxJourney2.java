package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class MaxJourney2 {

    @Description("Verify that YBL term journey is working")
    @Test
    public void verify_next_lesson_click() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

            // Enter email and income
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.sendKeys("prakhar.varshney@crestechsoftware.com");

            WebElement incomeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("eligibilityAnnualIncome")));
            incomeField.sendKeys("794738");

            // Proceed button double click with 2s gap
            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='viewPlans' and text()='Proceed']")));
            proceedButton.click();
            Thread.sleep(2000);
            proceedButton.click();

            Thread.sleep(7000);

            // Net Banking option
            WebElement netBanking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Net Banking']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", netBanking);
            netBanking.click();

            Thread.sleep(3000);

            // Select Bank (updated XPath and JS click fallback)
            WebElement bankButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(@class,'banks-icon') and contains(@class,'selectedIcon')]")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bankButton);
            try {
                bankButton.click();
            } catch (ElementNotInteractableException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bankButton);
            }

            Thread.sleep(3000);

            // Final Submit
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            submitButton.click();

            Thread.sleep(8000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btnd']"))).click();

            Thread.sleep(15000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
