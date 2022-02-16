package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeliveryPage extends BaseTest {

    WebDriver driver;
    public static final String EMAIL_UPDATE = "email_update";
    public static final String EMAIL = "email";
    public static final String DETAILS_DATA_TEXT = "details_text";
    public static final String VALID_DISCOUNT = "10OFF";
    public static final String APPLY_BUTTON = "Apply Button";


    public DeliveryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//bdo[contains(text(),'viola+2@skooli.com')]")
    WebElement contactData;

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/span[1]")
    WebElement changeBtn;

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/main[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/address[1]")
    WebElement detailsData;

    @FindBy(xpath = "//button[@id='continue_button']")
    WebElement continuePayment;

    @FindBy(xpath = "//bdo[contains(text(),'viola+3@skooli.com')]")
    WebElement emailUpdated;

    @FindBy(xpath = "//input[@id='checkout_reduction_code']")
    WebElement discountField;

    @FindBy(xpath = "//body/div[1]/div[1]/aside[1]/div[2]/div[1]/div[1]/div[2]/form[2]/div[1]/div[1]/div[1]/button[1]")
    WebElement applyButton;

    @FindBy(xpath = "//body/div[1]/div[1]/aside[1]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]")
    WebElement discountWarning;

    @FindBy(xpath = "//a[contains(text(),'Student information')]")
    WebElement studentLink;

    @FindBy(xpath = "//a[contains(text(),'Payment')]")
    WebElement paymentLink;


    // Confirm data displayed on contact and details
    public void confirmData() {
        waitForElement(driver, contactData);
        isTextDisplayed(contactData, properties.getProperty(EMAIL));
        waitForElement(driver, detailsData);
        isTextDisplayed(detailsData, properties.getProperty(DETAILS_DATA_TEXT));
    }

    // Click on change link to update the data
    public StudentInformationPage clickChangeLink() {
        waitForElement(driver, changeBtn);
        clickOnElement(driver, changeBtn);

        return new StudentInformationPage(driver);
    }

    // Confirm updated data displayed on contact and details
    public void confirmUpdatedData() {
        waitForElement(driver, emailUpdated);
        isTextDisplayed(emailUpdated, properties.getProperty(EMAIL_UPDATE));
    }

    // Click Continue to payment button
    public PaymentPage clickContinuePayment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continuePayment));
            clickOnElement(driver, continuePayment);
        } catch (TimeoutException e) {
            System.err.println("Unable to find the clickable element" + continuePayment);
        }
        catch (StaleElementReferenceException e){
            System.err.print("The button is not clickable: "+continuePayment);
            e.printStackTrace();
        }
        return new PaymentPage(driver);
    }

    //Apply invalid Coupon
    public void applyCoupon() {
        sendKeys(discountField, VALID_DISCOUNT);
//        discountField.sendKeys(VALID_DISCOUNT);
        clickOnElementAndDisplayeText(driver, applyButton, APPLY_BUTTON);
        waitForElement(driver, discountWarning);
    }

    public StudentInformationPage clickStudentLink(){
        clickOnElement(driver, studentLink);
        return new StudentInformationPage(driver);
    }

    public PaymentPage clickPaymentLink(){
        clickOnElement(driver, paymentLink);

        return new PaymentPage(driver);
    }
}
