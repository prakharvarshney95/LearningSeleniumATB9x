package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class New_User_Registration_Negative_Test_Cases {

        @Description("Verify all negative test cases of registration page for a new user")
        @Test
        public void new_user_registration() throws InterruptedException {

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--start-maximized");
            WebDriver driver = new EdgeDriver(edgeOptions);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.get("https://account.devnagri.com/register");
            driver.manage().window().maximize();

            // Test 1: All fields blank
            driver.findElement(By.xpath("//button[div=' Register']")).click();
            Thread.sleep(2000);

            // Test 2: Invalid First Name
            WebElement firstName = driver.findElement(By.id("first_name"));
            firstName.clear();
            firstName.sendKeys("@&^4jj");
            Thread.sleep(1000);

            // Test 3: Invalid Last Name
            WebElement lastName = driver.findElement(By.id("last_name"));
            lastName.clear();
            lastName.sendKeys("388964@#");

            // Test 4: Invalid Email
            WebElement email = driver.findElement(By.id("email"));
            email.clear();
            email.sendKeys("fakeemail.com");

            // Test 5: Invalid Country Code
            try {
                WebElement countryCodeDropdown = driver.findElement(By.xpath("//select[@id='country_code']"));
                countryCodeDropdown.click();
                WebElement invalidOption = driver.findElement(By.xpath("//option[@value='+671']"));
                invalidOption.click();
                System.out.println("Invalid country code selected");
            } catch (Exception e) {
                System.out.println("Invalid country code selection not applicable or element not found.");
            }

            // Test 6: Invalid Mobile Number
            WebElement mobile = driver.findElement(By.id("phone"));
            mobile.clear();
            mobile.sendKeys("123abc");

            // Test 7: Invalid Organization Name
            WebElement orgName = driver.findElement(By.id("organization_name"));
            orgName.clear();
            orgName.sendKeys("!!!@@@");

            // Test 8: Weak Password (less than 8 characters)
            WebElement password = driver.findElement(By.id("password"));
            password.clear();
            password.sendKeys("12345");

            // Test 9: Mismatched Confirm Password
            WebElement confirmPassword = driver.findElement(By.id("password_confirmation"));
            confirmPassword.clear();
            confirmPassword.sendKeys("12345678");

            // Test 10 : Wrong email formats
            String[] invalidEmails = {"test@.com", "test.com", "test@com", "@example.com", "test@@gmail.com"};
            for (String invalidEmail : invalidEmails) {
                email = driver.findElement(By.id("email"));
                email.clear();
                email.sendKeys(invalidEmail);
                driver.findElement(By.xpath("//button[div=' Register']")).click();
                Thread.sleep(1500);
                System.out.println("Tried invalid email: " + invalidEmail);
            }

            // Test 11 : Weak passwords
            String[] weakPasswords = {
                    "1234567",
                    "abcdefgh",
                    "12345678",
                    "abcd1234",
                    "!!!!@@@@",
                    "abc!@#"
            };

            for (String pwd : weakPasswords) {
                password = driver.findElement(By.id("password"));
                confirmPassword = driver.findElement(By.id("password_confirmation"));
                password.clear();
                confirmPassword.clear();
                password.sendKeys(pwd);
                confirmPassword.sendKeys(pwd);
                driver.findElement(By.xpath("//button[div=' Register']")).click();
                Thread.sleep(1500);
                System.out.println("Tried weak password: " + pwd);
            }

            // Test 12 : Already Existing Email
            email = driver.findElement(By.id("email"));
            email.clear();
            email.sendKeys("existinguser@gmail.com");
            driver.findElement(By.xpath("//button[div=' Register']")).click();
            Thread.sleep(2000);
            System.out.println("Tried with existing email.");

            // Wait for user to manually enter captcha
            System.out.println("Please enter captcha manually within 10 seconds...");
            Thread.sleep(10000);

            // Attempt to register
            driver.findElement(By.xpath("//button[div=' Register']")).click();
            Thread.sleep(2000);

            driver.quit();
        }
    }

