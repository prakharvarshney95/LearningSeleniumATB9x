package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class MaxJourney3_complete_till_upload {

    @Description("Verify that YBL term journey is working")
    @Test
    public void verify_next_lesson_click() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://neosit.axismaxlife.com/utp-service/api/banca/ybl/v1/processhandler?productCode=ssp&cid=uoPY5KT+POv8qwtKPWRLTw==&utm_medium=IRIS&utm_source=Whatsapp&utm_campaign=march_term");

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

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='submitSuccessForm()']"))).click();

            Thread.sleep(15000);

            // Net Banking
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Net Banking']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'banks-icon') and contains(@class,'selectedIcon')]"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search-input']"))).sendKeys("TEST BANK");
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='option  ' and text()='TEST BANK']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Account Holder's Name\"]"))).sendKeys("AMAN SINGH");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Account Number']"))).sendKeys("847788939356");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='label' and text()='Savings A/c']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='unified-button unified-button-primary unified-btn-default  w-full-imp mt-6']"))).click();
            Thread.sleep(3000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='cid']"))).sendKeys("test");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pwd']"))).sendKeys("test");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='proceed']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='proceed']"))).click();
            Thread.sleep(30000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('.user-input-form').click();");

            String afterContent = (String) js.executeScript(
                    "return window.getComputedStyle(document.querySelector('.user-input-form'), '::after').getPropertyValue('content');"
            );
            System.out.println("After content: " + afterContent);

            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Marital Status*' and contains(@class, 'maritalStatus-select')]"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='button' and span[text()='Single']]"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='altPhoneNumber']"))).sendKeys("9477858635");
            Thread.sleep(1000);

            WebElement fatherNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='fatherName' and text()=\"Father's Name*\"]")));
            js.executeScript("arguments[0].scrollIntoView(true);", fatherNameField);
            fatherNameField.click();
            fatherNameField.sendKeys("Anuj Rawat");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(2000);


            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@type='submit' and text()='Save & Proceed']")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@title, 'Industry type*')]"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'selectedLable') and text()='Other Than Above']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='industryTypeDetails']"))).sendKeys("Software");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='button' and text()='Software']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='employerName']"))).sendKeys("HCL");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='button' and text()='HCL Technologies']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@title, 'Organization type*')]"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'jsx-3790918559  false') and text()='Pvt Ltd']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='jobTitle']"))).sendKeys("Software Engineer");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(2000);

            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='No']")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='No']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(2000);

// Lifestyle Page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='weight']"))).sendKeys("82");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and contains(@title, 'Inches*')]"))).click();
            Thread.sleep(2000);

            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[@role='button' and contains(@tabindex, '10')]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='button' and contains(@tabindex, '10')]"))).click();
            Thread.sleep(1000);

            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@type='submit' and text()='Save & Proceed']")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='fullName-0' and text()='Nominee Full Name*']"))).sendKeys("Anisha rajdan");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='dob-0' and text()='Nominee Date of Birth*']"))).sendKeys("03081991");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='phoneNumber-0' and text()='Mobile Number*']"))).sendKeys("9623858355");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='email-0' and text()='Email Address']"))).sendKeys("abc@domain.com");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='relationWithNominee']"))).click();
            Thread.sleep(1000);

            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[@role='button' and contains(@tabindex, '6')]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='button' and contains(@tabindex, '6')]"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='motherFullName' and text()=\"Mother's Full Name*\"]"))).sendKeys("Parvati Mishra");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'jsx-3790918559 customPlaceholder cursor-pointer') and text()='Select Address Proof Type']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'jsx-3790918559  false') and text()='Aadhaar/UID Card']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='idProofAdharNumber']"))).sendKeys("8365");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Save & Proceed']"))).click();
            Thread.sleep(3000);

            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='Yes, I want Printed Policy Document']")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='Yes, I want Printed Policy Document']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='I accept the terms and conditions']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='Submit Proposal Form']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='unified-button unified-button-primary unified-btn-default   gtm-popupacceptreviewdeclaration' and text()='YES']"))).click();
            Thread.sleep(30000);


// Medical Home Visit Page
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='Yes']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'custom-checkbox__label') and text()='Home']"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pinCode']"))).sendKeys("201301");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='button' and contains(@tabindex, '0')]"))).click(); // e.g., sector option
            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='addressLine1']"))).sendKeys("B-34");
            Thread.sleep(500);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='addressLine2']"))).sendKeys("Sector 65");
            Thread.sleep(500);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='landmark']"))).sendKeys("Near Metro Station");
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'custom-checkbox__label') and contains(text(),'I confirm')]"))).click();
            Thread.sleep(1000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@type, 'submit') and text()='Save & Proceed']"))).click();
            Thread.sleep(5000);


// OTP Verification Page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OTP1"))).sendKeys("1");
            Thread.sleep(1000);

            driver.findElement(By.id("OTP2")).sendKeys("2");
            Thread.sleep(1000);

            driver.findElement(By.id("OTP3")).sendKeys("3");
            Thread.sleep(1000);

            driver.findElement(By.id("OTP4")).sendKeys("4");
            Thread.sleep(1000);

            driver.findElement(By.id("OTP5")).sendKeys("5");
            Thread.sleep(1000);

            driver.findElement(By.id("OTP6")).sendKeys("6");
            Thread.sleep(1000);

            WebElement validateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and text()='VALIDATE']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", validateButton);
            validateButton.click();

            Thread.sleep(10000); // Wait for post-validation redirection/confirmation

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

