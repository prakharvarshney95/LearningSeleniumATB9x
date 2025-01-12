package com.prakharvarshney95.ex17_DataDrivenTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSelenium48_TDDT {

    @DataProvider
    public Object [][] getData() {
        return new Object[][] {
                new Object[]{"admin@gmail.com", "pass@123"},
                new Object[]{"admin123@gmail.com", "pass@1234"},
                new Object[]{"admin12365@gmail.com", "pass12455"}

        };
    }



    @Test (dataProvider = "getData")
    public void loginTest(String email,String password) {
        System.out.println(email + " | " + password);

    }




}
