package com.teachaway.test.cases;

import com.teachaway.base.grid.BaseTest;
import com.teachaway.pages.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TorontoTeflOise100HTest extends BaseTest {

    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String ZIP_CODE = "zip_code";
    public static final String PHONE = "phone";
    public static final String EMAIL_UPDATE = "email_update";
    public static final String NAME_UPDATE = "name_update";
    public static final String ZIP_UPDATE = "zip_code_update";
    public static final String DISCOUNT_CODE = "BG23455";
    public static final String CONFIG_KEY_URL = "url";
    public static final String COUNTRY_OPTION = "country";
    public static final String STATE_OPTION = "state";

    @Parameters({"browser", "environment"})
    @Test

    // Toronto test case selected OISE TEFL Course 100 hours
    public void torontoTeflCourse100hTestCases() {
        driver.get(properties.getProperty(CONFIG_KEY_URL));
        MainPage mainPage = new MainPage(driver);
        TeflCertificationPage teflCertificationPage = new TeflCertificationPage(driver);
        EnrollPage enrollPage = new EnrollPage(driver);
        CartPage cartPage = new CartPage(driver);
        StudentInformationPage studentInformationPage = new StudentInformationPage(driver);
        DeliveryPage deliveryPage = new DeliveryPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);

        // Call all the methods created to the pages
        mainPage.clickTeflButton();
        teflCertificationPage.clickEnrollButton();
        teflCertificationPage.confirmCookies();
        enrollPage.clickEnrollNowButton100H();
        cartPage.checkCartDataCourse100H();
        cartPage.clickCheckoutButton();
        studentInformationPage.clickCartLink();
        cartPage.clickCheckoutButton();
        studentInformationPage.addCoupon(DISCOUNT_CODE);
        studentInformationPage.clickPolicy();
        studentInformationPage.fillForm(properties.getProperty(EMAIL), properties.getProperty(NAME), properties.getProperty(LASTNAME), properties.getProperty(ADDRESS), properties.getProperty(CITY), properties.getProperty(ZIP_CODE), properties.getProperty(PHONE), properties.getProperty(COUNTRY_OPTION), properties.getProperty(STATE_OPTION));
        deliveryPage.confirmData();
        deliveryPage.clickChangeLink();
        studentInformationPage.updateForm(properties.getProperty(EMAIL_UPDATE), properties.getProperty(NAME_UPDATE), properties.getProperty(ZIP_UPDATE));
        deliveryPage.confirmUpdatedData();
        deliveryPage.clickContinuePayment();
        paymentPage.setDifferentBillingAddress();
        paymentPage.confirmForm();
    }
}

