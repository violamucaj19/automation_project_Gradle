package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseTest {

    WebDriver driver;
    public static final String DIFFERENT_BILLING_ADDRESS_TEXT = "Use a different billing address";
    public static final String SAVE_CHECKBOX = "Save my information for a faster checkout checkbox";
    public static final String PHONE_FIELD = "Phone input";
    public static final String PHONE_INPUT = "123456";
    public static final String DELIVERY_LINK = "Delivery Link";


    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='checkout_different_billing_address_true']")
    WebElement differentBillingAddress;

    @FindBy(xpath = "//div[@id='section--billing-address__different']")
    WebElement differentBillingForm;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/bdo[1]")
    WebElement contactField;

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/address[1]")
    WebElement detailsField;

    @FindBy(xpath = "//input[@id='checkout_remember_me']")
    WebElement saveCheckbox;

    @FindBy(xpath = "//input[@id='checkout_vault_phone']")
    WebElement phoneInput;

    @FindBy(xpath = "//a[contains(text(),'Delivery')]")
    WebElement DeliveryLink;

    // Click to open the billing address form
    public void setDifferentBillingAddress() {
        waitForElement(driver, differentBillingAddress);
        clickOnElementAndDisplayeText(driver, differentBillingAddress, DIFFERENT_BILLING_ADDRESS_TEXT);
    }

    // Confirm that form is opened
    public void confirmForm() {
        isElementDisplayed(differentBillingForm);
    }

    // Check if contact and details are displayed correctly
    public void checkData(String contact, String details) {
        waitForElement(driver, contactField);
        waitForElement(driver, detailsField);
        isTextDisplayed(contactField, contact);
        isTextDisplayed(detailsField, details);
    }

    //Click Save Checkbox
    public void clickSaveCheckbox() {
        clickOnElementAndDisplayeText(driver, saveCheckbox, SAVE_CHECKBOX);
        waitForElement(driver, phoneInput);
        clickOnElementAndDisplayeText(driver, phoneInput, PHONE_FIELD);
        sendKeys(phoneInput, PHONE_INPUT);
    }

    public DeliveryPage clickDeliveryLink() {
        clickOnElementAndDisplayeText(driver, DeliveryLink, DELIVERY_LINK);
        return new DeliveryPage(driver);
    }
}
