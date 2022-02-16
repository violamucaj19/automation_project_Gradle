package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnrollPage extends BaseTest {

    WebDriver driver;
    public static final String CART_URL = "cartURL";
    public static final String ENROLL_NOW_TEXT = "Enroll Now TEFL Course OISE";
    public static final String OISE_PRODUCT_URL = "oiseTeflCourseProductUrl";


    public EnrollPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html[1]/body[1]/main[1]/section[2]/div[1]/slider-component[1]/ul[1]/li[1]/div[2]/div[1]/div[1]/div[2]/form[1]/input[3]")
    WebElement EnrollNowButton100Hours;

    @FindBy(xpath = "//body/main[@id='MainContent']/section[@id='shopify-section-template--15401420357890__16372566220ca750f2']/div[1]/slider-component[1]/ul[1]/li[2]/div[2]/div[1]/div[1]/div[2]/a[1]")
    WebElement EnrollNowButton120Hours;

    @FindBy(xpath = "//body/main[@id='MainContent']/section[@id='shopify-section-template--15401420357890__16372566220ca750f2']/div[1]/slider-component[1]/ul[1]/li[3]/div[2]/div[1]/div[1]/div[2]/a[1]")
    WebElement EnrollNowButton150Hours;

    @FindBy(id = "hs-eu-confirmation-button")
    WebElement ConfirmButton;

    // Confirm cookies
    public void confirmCookies() {
        driver.switchTo().window(driver.getWindowHandles().stream().reduce((f, s) -> s).orElse(null));
        waitForElement(driver, ConfirmButton);
        clickOnElement(driver, ConfirmButton);
    }

    // Click Enroll now Button for TEFL OISE Course 100 Hours
    public CartPage clickEnrollNowButton100H() {
        clickOnElementAndDisplayeText(driver, EnrollNowButton100Hours, ENROLL_NOW_TEXT);
        assertUrls(driver, properties.getProperty(CART_URL));

        return new CartPage(driver);
    }

    // Click Enroll now Button for TEFL OISE Course 120 Hours
    public ProductPage clickEnroll120hButton() {
        waitForElement(driver, EnrollNowButton120Hours);
        clickOnElement(driver, EnrollNowButton120Hours);

        return new ProductPage(driver);
    }

    // Click Enroll now Button for TEFL OISE Course 150 Hours
    public TeflOiseCourse150HPage clickEnrollNowButton150H() {
        clickOnElementAndAssertUrl(driver, EnrollNowButton150Hours, properties.getProperty(OISE_PRODUCT_URL));

        return new TeflOiseCourse150HPage(driver);
    }
}


