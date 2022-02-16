package com.teachaway.test.cases;

import com.teachaway.base.grid.BaseTest;
import com.teachaway.pages.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TorontoTeflRedirectionsTest extends BaseTest {
    public static final String CONFIG_KEY_URL = "url";
    public static final String REGION = "region";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String ADDRESS = "address";
    public static final String CITY = "city_canada";
    public static final String ZIP_CODE = "zip_code_canada";
    public static final String PHONE = "phone";
    public static final String DETAILS = "details_text_canada";
    public static final String COUNTRY_OPTION = "country_canada";
    public static final String STATE_OPTION = "state_canada";

    @Parameters({"browser", "environment"})
    @Test
    public void torontoTeflRedirection() {
        driver.get(properties.getProperty(CONFIG_KEY_URL));
        MainPage mainPage = new MainPage(driver);
        TeflCertificationGuidePage teflCertificationGuidePage = new TeflCertificationGuidePage(driver);
        TeflCertificationPage teflCertificationPage = new TeflCertificationPage(driver);
        OisePage oisePage = new OisePage(driver);
        EnrollPage enrollPage = new EnrollPage(driver);
        CartPage cartPage = new CartPage(driver);
        StudentInformationPage studentInformationPage = new StudentInformationPage(driver);
        DeliveryPage deliveryPage = new DeliveryPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);

        mainPage.clickTeflGuide();
        teflCertificationGuidePage.clickExploreButton();
        teflCertificationPage.clickGetStartedButton();
        oisePage.clickEnrollNowButton();
        enrollPage.confirmCookies();
        enrollPage.clickEnrollNowButton100H();
        cartPage.checkCartDataCourse100H();
        cartPage.changeRegionAndConfirmPrice(properties.getProperty(REGION));
        cartPage.clickCheckoutButton();
        studentInformationPage.fillForm(properties.getProperty(EMAIL), properties.getProperty(NAME), properties.getProperty(LASTNAME), properties.getProperty(ADDRESS), properties.getProperty(CITY), properties.getProperty(ZIP_CODE), properties.getProperty(PHONE), properties.getProperty(COUNTRY_OPTION), properties.getProperty(STATE_OPTION));
        deliveryPage.clickContinuePayment();
        studentInformationPage.checkTaxesAndPricesDisplayed();
        paymentPage.checkData(properties.getProperty(EMAIL), properties.getProperty(DETAILS));
        paymentPage.clickDeliveryLink();
        deliveryPage.clickStudentLink();
        studentInformationPage.clickCartLink();
        cartPage.clickCheckoutButton();
        studentInformationPage.clickDeliveryLink();
        deliveryPage.clickPaymentLink();
        studentInformationPage.checkTaxesAndPricesDisplayed();
    }
}