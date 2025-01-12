package com.prakharvarshney95.ex17_DataDrivenTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSelenium50_DataDriverExcel {

    @Test(dataProvider = "getData", dataProviderClass = UtilExcel.class)
    public void test_VWOLogin(String email, String password) {
        System.out.println("Email - " + email);
        System.out.println("Password - " + password);

        // If email and pass ->  correct ->

        /// Else


    }
}