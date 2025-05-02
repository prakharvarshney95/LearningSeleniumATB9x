package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MaxJourney_Optimized_code {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(20);  // Increased from 10
    private static final Duration SHORT_WAIT = Duration.ofMillis(500);
    private static final Duration LONG_WAIT = Duration.ofSeconds(30);
    private static final Duration WINDOW_WAIT = Duration.ofSeconds(20);
    private String mainWindowHandle;

    @BeforeMethod
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito", "--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, DEFAULT_WAIT);
        js = (JavascriptExecutor) driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Description("Verify that YBL term journey is working")
    @Test
    public void verifyYBLTermJourney() {
        try {
            // Store test data
            Map<String, String> testData = initializeTestData();

            // Navigate to application
            navigateToApplication("https://neouat.axismaxlife.com/utp-service/api/banca/ybl/v1/processhandler?productCode=ssp&cid=uoPY5KT+POv8qwtKPWRLTw==&utm_medium=IRIS&utm_source=Whatsapp&utm_campaign=march_term");

            // Execute test steps
            executeTestSteps(testData);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Map<String, String> initializeTestData() {
        Map<String, String> testData = new HashMap<>();
        testData.put("email", "prakhar.varshney@crestechsoftware.com");
        testData.put("income", "794738");
        testData.put("altPhone", "9477858635");
        testData.put("fatherName", "Anuj Rawat");
        testData.put("industryType", "Software");
        testData.put("employer", "HCL");
        testData.put("jobTitle", "Software Engineer");
        testData.put("weight", "82");
        testData.put("nomineeName", "Anisha rajdan");
        testData.put("nomineeDob", "03081991");
        testData.put("nomineePhone", "9623858355");
        testData.put("nomineeEmail", "abc@domain.com");
        testData.put("motherName", "Parvati Mishra");
        testData.put("aadhaar", "8365");
        testData.put("pincode", "201301");
        testData.put("address1", "B-34");
        testData.put("address2", "Sector 65");
        testData.put("landmark", "Near Metro Station");
        return testData;
    }

    private void executeTestSteps(Map<String, String> testData) {
        fillPersonalDetails();
        clickProceedButton();
        answerHealthQuestions();
        fillContactInformation(testData);
        selectPaymentMethod();
        fillPersonalInformation(testData);
        fillEmploymentDetails(testData);
        fillLifestyleInformation(testData);
        fillNomineeInformation(testData);
        fillKYCDetails(testData);
        acceptTermsAndSubmit();
        scheduleMedicalVisit(testData);
        verifyOTP();
    }

    private void navigateToApplication(String url) {
        driver.get(url);
        mainWindowHandle = driver.getWindowHandle(); // Store main window handle
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
            Thread.sleep(1000); // Additional stabilization wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void fillPersonalDetails() {
        clickElement(By.xpath("//span[text()='5 - 7']"));
        clickElement(By.xpath("//span[text()='No']"));
        clickElement(By.xpath("//span[text()='Salaried']"));
        clickElement(By.xpath("//span[text()='Graduate & Above']"));
        clickElement(By.xpath("//button[@id='viewPlans' and text()='Calculate Now']"));
        waitForPageLoad();
    }

    private void clickProceedButton() {
        clickElement(By.xpath("//button[@id='viewPlans' and text()='Proceed']"));
        waitForPageLoad();
    }

    private void answerHealthQuestions() {
        clickElement(By.xpath("//span[text()='No']"));
        clickElement(By.xpath("//button[@id='viewPlans' and text()='Skip']"));
        waitForPageLoad();
    }

    private void fillContactInformation(Map<String, String> testData) {
        enterText(By.id("email"), testData.get("email"));
        enterText(By.id("eligibilityAnnualIncome"), testData.get("income"));

        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@id='viewPlans' and text()='Proceed']")));
        proceedButton.click();
        waitForShortDuration();
        proceedButton.click();
        waitForLongDuration();
    }

    private void selectPaymentMethod() {
        clickElementWithScroll(By.xpath("//p[text()='Net Banking']"));
        waitForPageLoad();

        // Select Bank
        WebElement bankButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'banks-icon') and contains(@class,'selectedIcon')]")));
        clickWithJsFallback(bankButton);
        waitForPageLoad();

        // Final Submit - opens confirmation window
        clickElementWithScroll(By.xpath("//button[@type='submit']"));
        waitForLongDuration();

        // ===== Handle Confirmation Window =====
        switchToNewWindow("Confirmation");
        try {
            // Handle confirmation in new window
            clickWithRetry(By.xpath("//button[@onclick='submitSuccessForm()']"));
            waitForLongDuration();
        } finally {
            // Close confirmation window and return to main
            driver.close();
            driver.switchTo().window(mainWindowHandle);
        }

        // Continue in main window - fill account details
        fillBankDetails();

        // ===== Handle Login Window =====
        switchToNewWindow("Login");
        try {
            // Handle login in new window
            enterText(By.xpath("//input[@name='cid']"), "test");
            waitForShortDuration();

            enterText(By.xpath("//input[@name='pwd']"), "test");
            waitForShortDuration();

            clickWithRetry(By.xpath("//input[@name='proceed']"));
            waitForShortDuration();

            clickWithRetry(By.xpath("//input[@name='proceed']"));
            waitForLongDuration();
        } finally {
            driver.switchTo().window(mainWindowHandle);
        }
    }

    private void fillBankDetails() {
        clickElement(By.xpath("//p[text()='Net Banking']"));
        waitForShortDuration();

        clickElement(By.xpath("//button[contains(@class,'banks-icon') and contains(@class,'selectedIcon')]"));
        waitForShortDuration();

        enterText(By.xpath("//input[@id='search-input']"), "TEST BANK");
        waitForShortDuration();

        clickElement(By.xpath("//li[@class='option  ' and text()='TEST BANK']"));
        waitForShortDuration();

        enterText(By.xpath("//input[@placeholder=\"Account Holder's Name\"]"), "AMAN SINGH");
        waitForShortDuration();

        enterText(By.xpath("//input[@placeholder='Account Number']"), "847788939356");
        waitForShortDuration();

        clickElement(By.xpath("//span[@class='label' and text()='Savings A/c']"));
        waitForShortDuration();

        clickElement(By.xpath("//button[contains(@class,'unified-button-primary')]"));
        waitForPageLoad();
    }

    // ========== WINDOW HANDLING METHODS ==========
    private void switchToNewWindow(String windowPurpose) {
        debugWindowState("Before switching to " + windowPurpose);

        // Wait for new window to appear
        WebDriverWait windowWait = new WebDriverWait(driver, WINDOW_WAIT);
        windowWait.until(d -> {
            Set<String> handles = d.getWindowHandles();
            return handles.size() > 1;
        });

        // Switch to new window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Wait for new window to be ready
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        debugWindowState("After switching to " + windowPurpose);
    }

    private void debugWindowState(String message) {
        System.out.println("\n=== " + message + " ===");
        System.out.println("Current Window Handle: " + driver.getWindowHandle());
        System.out.println("All Window Handles: " + driver.getWindowHandles());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle() + "\n");
    }

    // ========== ENHANCED ELEMENT INTERACTION METHODS ==========
    private void clickWithRetry(By locator) {
        try {
            clickElement(locator);
        } catch (TimeoutException e) {
            System.out.println("Standard click failed, trying JavaScript click");
            WebElement element = driver.findElement(locator);
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
        }
    }

    // ========== EXISTING HELPER METHODS (remain unchanged) ==========
    private void fillPersonalInformation(Map<String, String> testData) {
        js.executeScript("document.querySelector('.user-input-form').click();");

        clickElement(By.xpath("//button[@title='Marital Status*']"));
        clickElement(By.xpath("//a[@role='button' and span[text()='Single']]"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='altPhoneNumber']"), testData.get("altPhone"));
        waitForShortDuration();

        WebElement fatherNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[@for='fatherName' and text()=\"Father's Name*\"]")));
        scrollAndClick(fatherNameField);
        fatherNameField.sendKeys(testData.get("fatherName"));
        waitForShortDuration();

        clickSubmitButton();
    }

    private void fillEmploymentDetails(Map<String, String> testData) {
        clickElement(By.xpath("//button[contains(@title, 'Industry type*')]"));
        clickElement(By.xpath("//span[text()='Other Than Above']"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='industryTypeDetails']"), testData.get("industryType"));
        waitForShortDuration();

        clickElement(By.xpath("//a[@role='button' and text()='Software']"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='employerName']"), testData.get("employer"));
        waitForShortDuration();

        clickElement(By.xpath("//a[@role='button' and text()='HCL Technologies']"));
        waitForShortDuration();

        clickElement(By.xpath("//button[contains(@title, 'Organization type*')]"));
        clickElement(By.xpath("//span[text()='Pvt Ltd']"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='jobTitle']"), testData.get("jobTitle"));
        waitForShortDuration();

        clickSubmitButton();
    }

    private void fillLifestyleInformation(Map<String, String> testData) {
        enterText(By.xpath("//input[@name='weight']"), testData.get("weight"));
        waitForShortDuration();

        clickElement(By.xpath("//button[contains(@title, 'Inches*')]"));
        waitForShortDuration();

        clickElement(By.xpath("//a[@role='button' and contains(@tabindex, '10')]"));
        waitForShortDuration();

        clickSubmitButton();
    }

    private void fillNomineeInformation(Map<String, String> testData) {
        enterText(By.xpath("//label[@for='fullName-0']"), testData.get("nomineeName"));
        waitForShortDuration();

        enterText(By.xpath("//label[@for='dob-0']"), testData.get("nomineeDob"));
        waitForShortDuration();

        enterText(By.xpath("//label[@for='phoneNumber-0']"), testData.get("nomineePhone"));
        waitForShortDuration();

        enterText(By.xpath("//label[@for='email-0']"), testData.get("nomineeEmail"));
        waitForShortDuration();

        clickElement(By.xpath("//input[@name='relationWithNominee']"));
        waitForShortDuration();

        clickElement(By.xpath("//a[@role='button' and contains(@tabindex, '6')]"));
        waitForShortDuration();

        clickSubmitButton();
    }

    private void fillKYCDetails(Map<String, String> testData) {
        enterText(By.xpath("//label[@for='motherFullName']"), testData.get("motherName"));
        waitForShortDuration();

        clickElement(By.xpath("//span[contains(text(),'Select Address Proof Type')]"));
        clickElement(By.xpath("//span[text()='Aadhaar/UID Card']"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='idProofAdharNumber']"), testData.get("aadhaar"));
        waitForShortDuration();

        clickSubmitButton();
        waitForPageLoad();
    }

    private void acceptTermsAndSubmit() {
        clickElement(By.xpath("//span[contains(text(),'Yes, I want Printed Policy Document')]"));
        waitForShortDuration();

        clickElement(By.xpath("//span[contains(text(),'I accept the terms and conditions')]"));
        waitForShortDuration();

        clickElement(By.xpath("//button[text()='Submit Proposal Form']"));
        waitForShortDuration();

        clickElement(By.xpath("//button[contains(@class,'gtm-popupacceptreviewdeclaration') and text()='YES']"));
        waitForLongDuration();
    }

    private void scheduleMedicalVisit(Map<String, String> testData) {
        clickElement(By.xpath("//span[contains(text(),'Yes')]"));
        waitForShortDuration();

        clickElement(By.xpath("//span[contains(text(),'Home')]"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='pinCode']"), testData.get("pincode"));
        waitForShortDuration();

        clickElement(By.xpath("//a[@role='button' and contains(@tabindex, '0')]"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='addressLine1']"), testData.get("address1"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='addressLine2']"), testData.get("address2"));
        waitForShortDuration();

        enterText(By.xpath("//input[@name='landmark']"), testData.get("landmark"));
        waitForShortDuration();

        clickElement(By.xpath("//span[contains(text(),'I confirm')]"));
        waitForShortDuration();

        clickElement(By.xpath("//button[text()='Save & Proceed']"));
        waitForPageLoad();
    }

    private void verifyOTP() {
        enterText(By.id("OTP1"), "1");
        enterText(By.id("OTP2"), "2");
        enterText(By.id("OTP3"), "3");
        enterText(By.id("OTP4"), "4");
        enterText(By.id("OTP5"), "5");
        enterText(By.id("OTP6"), "6");
        waitForShortDuration();

        clickElementWithScroll(By.xpath("//button[text()='VALIDATE']"));
        waitForLongDuration();
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void clickElementWithScroll(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.click();
    }

    private void scrollAndClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.click();
    }

    private void clickWithJsFallback(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private void clickSubmitButton() {
        clickElementWithScroll(By.xpath("//button[text()='Save & Proceed']"));
        waitForShortDuration();
    }

    private void waitForShortDuration() {
        try {
            Thread.sleep(SHORT_WAIT.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void waitForLongDuration() {
        try {
            Thread.sleep(LONG_WAIT.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}