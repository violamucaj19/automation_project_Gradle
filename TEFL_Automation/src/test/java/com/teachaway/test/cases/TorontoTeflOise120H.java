package com.teachaway.test.cases;

import com.teachaway.base.grid.BaseTest;
import com.teachaway.pages.*;
import org.testng.annotations.Test;

public class TorontoTeflOise120H extends BaseTest {

    public static final int CLICKS = 1;
    public static final String INVALID_EMAIL = "erta+2";
    public static final String INVALID_ZIP_CODE = "1234";
    public static final String NAME = "name";
    public static final String LAST_NAME = "lastname";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String ZIP_CODE = "zip_code";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String DETAILS = "details_text";
    public static final String CONFIG_KEY_URL = "url";
    public static final String COUNTRY_OPTION = "country";
    public static final String STATE_OPTION = "state";
    public static final String DISCOUNT_CODE = "discountCode";

    @Test

    // Toronto test case selected OISE TEFL Course 120 hours
    public void torontoTeflCourse120hTestCases() {
        driver.get(properties.getProperty(CONFIG_KEY_URL));
        MainPage mainPage = new MainPage(driver);
        TeflCertificationGuidePage teflCertificationGuide = new TeflCertificationGuidePage(driver);
        TeflCertificationPage teflCertification = new TeflCertificationPage(driver);
        EnrollPage enrollPage = new EnrollPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        StudentInformationPage studentInformation = new StudentInformationPage(driver);
        DeliveryPage deliveryPage = new DeliveryPage(driver);
        PaymentPage paymentPage = new PaymentPage(driver);

        mainPage.clickTeflGuide();
        teflCertificationGuide.clickExploreButton();
        teflCertification.clickEnrollButton();
        teflCertification.confirmCookies();
        enrollPage.clickEnroll120hButton();
        productPage.clickAddButton();
        cartPage.checkCartData120h();
        cartPage.clickMoreButton(CLICKS);
        cartPage.clickCheckoutButton();
        studentInformation.fillFormIncorrectly(INVALID_EMAIL, properties.getProperty(NAME), properties.getProperty(LAST_NAME), properties.getProperty(ADDRESS), properties.getProperty(CITY), INVALID_ZIP_CODE, properties.getProperty(PHONE), properties.getProperty(COUNTRY_OPTION), properties.getProperty(STATE_OPTION));
        studentInformation.checkDeliveryLink();
        studentInformation.updateForm(properties.getProperty(EMAIL), properties.getProperty(NAME), properties.getProperty(ZIP_CODE));
        deliveryPage.confirmData();
//        studentInformation.addCoupon(properties.getProperty(DISCOUNT_CODE));
        deliveryPage.clickContinuePayment();
        paymentPage.checkData(properties.getProperty(EMAIL), properties.getProperty(DETAILS));
        paymentPage.clickSaveCheckbox();
    }
}
