package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentInformationPage extends BaseTest {

    WebDriver driver;
    public static final String CART_URL = "cartUrl";
    public static final String REFUND_POLICY_TEXT = "Refund Policy";
    public static final String CLOSE_BUTTON_TEXT = "Close Pop Up Window";
    public static final String PRIVACY_POLICY_TEXT = "Privacy Policy";
    public static final String TERMS_SERVICE_TEXT = "Terms of Service";
    public static final String APPLY_BUTTON_TEXT = "Apply Discount Code";
    public static final String TOTAL_VALUE_COURSES_100H_150H = "total_value_100H_150H";
    public static final String TAX = "$103.48";
    public static final String SUBTOTAL = "$796.00";
    public static final String TOTAL = "$899.48";
    public static final String DISCOUNT_CODE = "discountCode";


    public StudentInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Cart')]")
    WebElement cartLink;

    @FindBy(xpath = "//span[contains(text(),'Delivery')]")
    WebElement DeliveryLink;

    @FindBy(xpath = "//input[@placeholder='Discount code']")
    WebElement discountCode;

//    @FindBy(xpath = "//button[@id='checkout_submit']")
    @FindBy(xpath = "//button[@id='checkout[submit]t']")
//    @FindBy(xpath = "//div[@class='_2qx1A _1Du_i _6mz7V _38isp']//button[@type='submit']")
    WebElement applyButton;

//    @FindBy(xpath = "//p[@id='error-for-reduction_code']")
    @FindBy(xpath = "//input[@name='reductions']")
    WebElement errorDiscountMessage;

    @FindBy(xpath = "//p[@id='error-for-zip']")
    WebElement zipMessage;

    @FindBy(xpath = "//p[@id='error-for-email']")
    WebElement emailMessage;

    @FindBy(xpath = "//a[contains(text(),'Refund policy')]")
    WebElement refundPolicy;

    @FindBy(xpath = "//a[contains(text(),'Privacy policy')]")
    WebElement privacyPolicy;

    @FindBy(xpath = "//a[contains(text(),'Terms of service')]")
    WebElement termService;

    @FindBy(className = "modal__close-button")
    WebElement CloseButtonPolicy;

//    @FindBy(xpath = "//input[@id='checkout_email']")
    @FindBy(xpath = "//input[contains(@id, 'email')]")
    WebElement emailCheck;

    @FindBy(xpath = "//input[contains(@name,'first') or contains(@id,'first')]")
    WebElement nameCheck;

    @FindBy(xpath = "//input[contains(@name,'last') or contains(@id,'last')]")
    WebElement lastNameCheck;

    @FindBy(xpath = "//input[contains(@name, 'address1') or contains(@id,'address1')]")
    WebElement addressCheck;

    @FindBy(xpath = "//input[contains(@name, 'city') or contains(@id,'city')]")
    WebElement cityCheck;

    @FindBy(xpath = "//input[contains(@name, 'zip') or contains(@name, 'postal') or contains(@id, 'zip')]")
    WebElement zipCodeCheck;

    @FindBy(xpath = "//input[contains(@name, 'phone') or contains(@id,'phone')]")
    WebElement phoneCheck;

    @FindBy(xpath = "//span[contains(text(),'Continue to delivery')]")
    WebElement continueDelivery;

    @FindBy(xpath = "//select[contains(@name, 'country')]")
    WebElement countryDropDownButton;

    @FindBy(xpath = "//select[contains(@name, 'province') or  contains(@name, 'zone')]")
    WebElement stateDropDownButton;

    @FindBy(xpath = "//span[@class='payment-due__price skeleton-while-loading--lg']")
    WebElement totalPrice;

    @FindBy(xpath = "//tr[contains(@class,'total-line total-line--taxes')]//td[@class='total-line__price']")
    WebElement taxes;

    @FindBy(xpath = "//body/div[1]/div[1]/aside[1]/div[2]/div[1]/div[1]/div[3]/table[1]/tfoot[1]/tr[1]/td[1]/span[2]")
    WebElement total;

    @FindBy(xpath = "//tbody/tr[1]/td[1]/span[1]")
    WebElement subtotalValue;

    @FindBy(xpath = "//a[contains(text(),'Delivery')]")
    WebElement deliveryLinkActivate;

    @FindBy(xpath = "//span[@class='tag__text']//span[@class='reduction-code']")
    WebElement reductionCode;


    // Click on Cart link to navigate back
    public CartPage clickCartLink() {
        waitForElement(driver, cartLink);
        clickOnElementAndAssertUrl(driver, cartLink, properties.getProperty(CART_URL));

        return new CartPage(driver);
    }

    // Add coupon
    public void addCoupon(String coupon) {
        waitForElement(driver, discountCode);
        sendKeys(driver, discountCode, coupon);
        clickOnElementAndDisplayeText(driver, applyButton, APPLY_BUTTON_TEXT);
        if (coupon.equals(properties.getProperty(DISCOUNT_CODE))) {
            waitForElement(driver, reductionCode);
            try {
                isTextDisplayed(reductionCode, coupon);
                log.info("The coupon: " + reductionCode.getText() + " is applied.");
            } catch (NoSuchElementException e) {
                System.err.println("Unable to locate element " + reductionCode);
            }
        } else {
            waitForElement(driver, errorDiscountMessage);
            try {
                log.info("Error message is displayed: " + errorDiscountMessage.getText());
            } catch (NoSuchElementException e) {
                System.err.println("Unable to locate element " + errorDiscountMessage);
            }
        }
    }

    // Click policy links
    public void clickPolicy() {
        clickOnElementAndDisplayeText(driver, refundPolicy, REFUND_POLICY_TEXT);
        clickOnElementAndDisplayeText(driver, CloseButtonPolicy, CLOSE_BUTTON_TEXT);
        waitForElement(driver, privacyPolicy);
        clickOnElementAndDisplayeText(driver, privacyPolicy, PRIVACY_POLICY_TEXT);
        clickOnElementAndDisplayeText(driver, CloseButtonPolicy, CLOSE_BUTTON_TEXT);
        waitForElement(driver, termService);
        clickOnElementAndDisplayeText(driver, termService, TERMS_SERVICE_TEXT);
        clickOnElementAndDisplayeText(driver, CloseButtonPolicy, CLOSE_BUTTON_TEXT);
    }

    // Fill student information form
    public DeliveryPage fillForm(String email, String name, String lastname, String address, String city, String zipCode, String phone, String countryOption, String stateOption) {
//        waitForElement(driver, emailCheck);
        sendKeys(driver,emailCheck, email);
        sendKeys(driver,nameCheck, name);
        sendKeys(driver, lastNameCheck, lastname);
        sendKeys(driver, addressCheck, address);
        sendKeys(driver, cityCheck, city);
        selectDropdownItem(countryDropDownButton, countryOption);
        selectDropdownItem(stateDropDownButton, stateOption);
        sendKeys(driver, zipCodeCheck, zipCode);
//        waitForElement(driver, phoneCheck);
        sendKeys(driver, phoneCheck, phone);
//        waitForElement(driver, continueDelivery);
        clickOnElement(driver, continueDelivery);

        return new DeliveryPage(driver);
    }

    // Update student information form
    public DeliveryPage updateForm(String email, String name, String zip) {
        waitForElement(driver, emailCheck);
        clearData(emailCheck);
        sendKeys(driver,emailCheck, email);
        waitForElement(driver, nameCheck);
        clearData(nameCheck);
        sendKeys(driver, nameCheck, name);
        clearData(zipCodeCheck);
        sendKeys(driver, zipCodeCheck, zip);
        clickOnElement(driver, continueDelivery);

        return new DeliveryPage(driver);
    }

    //Fill form by entering invalid email and zip-code
    public void fillFormIncorrectly(String email, String name, String lastname, String address, String city, String zipCode, String phone, String countryOption, String stateOption) {
        fillForm(email, name, lastname, address, city, zipCode, phone, countryOption, stateOption);
        waitForElement(driver, zipMessage);
        try {
            log.info("The error message is displayed: " + zipMessage.getText());
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element: "+ zipMessage);
        }
        waitForElement(driver, emailMessage);
        try {
            log.info("The error message is displayed: " + emailMessage.getText());
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element: "+ emailMessage);
        }
    }

    public void confirmTotalValuePrice() {
        isTextDisplayed(totalPrice, properties.getProperty(TOTAL_VALUE_COURSES_100H_150H));
    }

    public void checkDeliveryLink() {
        try {
            if (DeliveryLink.getTagName().equals("span")) {
                log.info(DeliveryLink.getText() + " is not clickable \n");
            }
        } catch (NoSuchElementException e) {
            System.err.println("Unable to locate element" + DeliveryLink);
            e.printStackTrace();
        }
    }

    public DeliveryPage clickDeliveryLink() {
        clickOnElement(driver, deliveryLinkActivate);

        return new DeliveryPage(driver);
    }

    public void checkTaxesAndPricesDisplayed() {
        waitForElement(driver, totalPrice);
        isTextDisplayed(taxes, TAX);
        isTextDisplayed(total, TOTAL);
        isTextDisplayed(subtotalValue, SUBTOTAL);
    }
}
