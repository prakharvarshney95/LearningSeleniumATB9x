package com.prakharvarshney95.ex17_DataDrivenTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSelenium49_TDDT_Parallel {

    @DataProvider(name = "LoginData", parallel = true)
    public Object [][] getData() {
        return new Object[][] {
                new Object[]{"admin@gmail.com", "pass@123"},
                new Object[]{"admin123@gmail.com", "pass@1234"},
                new Object[]{"admin12365@gmail.com", "pass12455"}

        };
    }

    @DataProvider(name = "LoginData2", parallel = true)
    public Object [][] getData2() {
        return new Object[][] {
                new Object[]{"admin@gmail.com", "pass@123"},
                new Object[]{"admin123@gmail.com", "pass@1234"},
                new Object[]{"admin12365@gmail.com", "pass12455"}

        };
    }


    @Test (dataProvider = "LoginData")
    public void loginTest(String email,String password) {
        System.out.println(email + " | " + password);

    }

    @Test (dataProvider = "LoginData2")
    public void loginTest2(String email,String password) {
        System.out.println(email + " | " + password);

    }


}
