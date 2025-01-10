package com.prakharvarshney95.task01;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class TestSelenium26 {

    @Test(groups = "QA")
    @Description("Test Case Description")
    public void test_web_table_login() throws Exception {

        WebDriver driver = new EdgeDriver();
        driver.get("https://www.amcharts.com/svg-maps/?map=india");
        driver.manage().window().maximize();

        // States - // //*[local-name()='svg']/*[name()="g"][7]/*[name()="g"]/*[name()="g"]/*[name()="path"]

        List<WebElement> states = driver.findElements(By.xpath("//*[name()='svg']/*[name()='g'][7]/*[name()='g']/*[name()='g']/*[name()='path']"));
                states.get(5).click();

        Thread.sleep(5000);
        driver.quit();

    }

}