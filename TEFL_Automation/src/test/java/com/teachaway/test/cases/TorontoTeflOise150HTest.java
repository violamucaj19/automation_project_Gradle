package com.teachaway.test.cases;

import com.teachaway.base.grid.BaseTest;
import com.teachaway.pages.*;
import org.testng.annotations.Test;

public class TorontoTeflOise150HTest  extends BaseTest {

    public static final String CONFIG_KEY_URL = "url";
    public static final String DISCOUNT_CODE = "discountCode";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String ZIP_CODE = "zip_code";
    public static final String PHONE = "phone";
    public static final String COUNTRY_OPTION = "country";
    public static final String STATE_OPTION = "state";

    @Test

    // Toronto test case selected OISE TEFL Course 150H and TEFL Course 100H
    public void torontoTeflCourse150hTestCases() {
        driver.get(properties.getProperty(CONFIG_KEY_URL));
        MainPage mainPage = new MainPage(driver);
        TeflCertificationPage teflCertificationPage = new TeflCertificationPage(driver);
        EnrollPage enrollPage = new EnrollPage(driver);
        CartPage cartPage = new CartPage(driver);
        StudentInformationPage studentInformationPage = new StudentInformationPage(driver);
        DeliveryPage deliveryPage = new DeliveryPage(driver);
        OisePage oisePage = new OisePage(driver);
        TeflOiseCourse150HPage teflOiseCourse150H = new TeflOiseCourse150HPage(driver);
        CollectionsPage collectionsPage = new CollectionsPage(driver);
        TeflOiseCourse100HPage teflOiseCourse100HPage = new TeflOiseCourse100HPage(driver);

        // Call all the methods created to the pages
        mainPage.clickTeflButton();
        teflCertificationPage.clickGetStartedButton();
        oisePage.clickEnrollNowButton();
        enrollPage.confirmCookies();
        enrollPage.clickEnrollNowButton150H();
        teflOiseCourse150H.clickAddCartButton();
        cartPage.checkCartDataCourse150h();
        cartPage.clickContinueShooping();
        collectionsPage.selectOiseCourse100H();
        teflOiseCourse100HPage.clickAddCartButton();
        cartPage.checkCartDataCourse100H();
        cartPage.checkCartDataSecondItem();
        cartPage.confirmSubtotalPrice();
        cartPage.clickCheckoutButton();
//        studentInformationPage.addCoupon(properties.getProperty(DISCOUNT_CODE));
//        studentInformationPage.confirmTotalValuePrice();
        studentInformationPage.fillForm(properties.getProperty(EMAIL), properties.getProperty(NAME), properties.getProperty(LASTNAME), properties.getProperty(ADDRESS), properties.getProperty(CITY), properties.getProperty(ZIP_CODE), properties.getProperty(PHONE), properties.getProperty(COUNTRY_OPTION), properties.getProperty(STATE_OPTION));
        deliveryPage.confirmData();
    }
}
