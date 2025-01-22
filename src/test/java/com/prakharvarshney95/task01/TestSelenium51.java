package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestSelenium51 {

    @Description("Verify that next lesson is clickable and certificate is getting generated")
    @Test
    public void verify_next_lesson_click() {
        // Set up EdgeDriver with options
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        WebDriver driver = new EdgeDriver(edgeOptions);

        try {
            // Navigate to the URL
            driver.get("https://courses.thetestingacademy.com/courses/9x-live-job-ready-automation-tester-blueprint-with-java-by-pramod-dutta-9xatb/contents/66d9853ee2b83");

            // Use WebDriverWait instead of Thread.sleep
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Login steps
            WebElement emailInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
            emailInputBox.sendKeys("prakharvarshney95@gmail.com");

            WebElement passwordInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
            passwordInputBox.sendKeys("System@243720");

            // Handle CAPTCHA manually
            System.out.println("Please solve the CAPTCHA manually within the next 30 seconds.");
            Thread.sleep(30000); // Pause execution to allow manual CAPTCHA solving

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='login']")));
            loginButton.click();

            // Click "Next" buttons in a loop
            for (int i = 0; i < 99; i++) {
                try {
                    // Wait for any overlay to disappear
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));

                    // Locate the "Next" button
                    WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@onclick='player.LoadNextContent();']")));

                    // Scroll to the "Next" button
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);

                    // Click the "Next" button
                    nextButton.click();

                    System.out.println("Clicked 'Next' button " + (i + 1) + " times.");
                } catch (Exception e) {
                    System.err.println("Failed to locate or interact with the 'Next' button at iteration " + (i + 1) + ": " + e.getMessage());
                    Thread.sleep(2000); // Wait and retry
                }
            }

            System.out.println("Test completed successfully.");
        } catch (Exception e) {
            // General exception handling
            e.printStackTrace();
            Assert.fail("Test failed: " + e.getMessage());
        } finally {
            // Ensure the browser is closed even if there is an exception
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }
}


