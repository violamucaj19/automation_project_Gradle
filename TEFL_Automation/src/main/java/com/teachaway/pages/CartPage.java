package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BaseTest {

    WebDriver driver;
    public static final String PRICE_100_HOUR_COURSE_TEXT = "price_course_100_hour";
    public static final String PRICE_100_HOUR_COURSE_CANADA_TEXT = "price_course_100_hour_canada";
    public static final String QUANTITY_100_HOUR_COURSE_TEXT = "1";
    public static final String QUANTITY_120H = "1";
    public static final String PRICE_120H = "prize120h";
    public static final String TITLE_120H = "120-hour OISE TEFL course";
    public static final String OISE_TEFL_COURSE_TEXT = "100-hour OISE TEFL course";
    public static final String OISE_COURSE_150H_TEXT = "150-hour OISE TEFL course";
    public static final String OISE_COURSE_150H_PRICE = "price_course_150_hour";
    public static final String COLLECTIONS_URL = "collectionsURL";
    public static final String SUBTOTAL_VALUE_COURSES_100H_150H = "subtotal_value_100H_150H";
    public static final String MORE_BUTTON = "More Button";
    public static final String COUNTRY_XPATH = "//body//div[@id='shopify-section-footer']//button[@aria-describedby='FooterCountryLabel']";
    public static final String PRICE_PATH = "/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]";

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='Quantity-1']")
    WebElement quantity100h;

    @FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/cart-items[1]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]/quantity-input[1]/input[1]")
    WebElement quantity120h;

    @FindBy(xpath = "//td[@class='cart-item__totals right small-hide']//div[@class='cart-item__price-wrapper']//dl[@class='cart-item__discounted-prices']//dd[@class='price price--end'][contains(text(),'$796.00')]")
    WebElement price100h;

    @FindBy(xpath = "//p[@class='totals__subtotal-value']")
    WebElement price120h;

    @FindBy(xpath = "//td[@class='cart-item__totals right small-hide']//div[@class='cart-item__price-wrapper']//dl[@class='cart-item__discounted-prices']//dd[@class='price price--end'][contains(text(),'$1,196.00')]")
    WebElement price150h;

    @FindBy(xpath = "//a[contains(text(),'100-hour OISE TEFL course')]")
    WebElement oiseTeflCourse;

    @FindBy(xpath = "//a[contains(text(),'120-hour OISE TEFL course')]")
    WebElement oiseTeflCourse120h;

    @FindBy(xpath = "//tbody/tr[@id='CartItem-1']/td[2]")
    WebElement cartItem;

    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkoutButton;

    @FindBy(xpath = "//body/main[@id='MainContent']/div[@id='shopify-section-template--15361889042690__cart-items']/cart-items[1]/div[1]/a[1]")
    WebElement continueShopping;

    @FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/cart-items[1]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]/quantity-input[1]/button[2]")
    WebElement moreButton;

    @FindBy(xpath = "//a[contains(text(),'150-hour OISE TEFL course')]")
    WebElement oiseCourse150h;

    @FindBy(xpath = "//p[@class='totals__subtotal-value']")
    WebElement subtotalValue;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//button[@aria-describedby='FooterCountryLabel']")
    WebElement regionButton;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[@data-value='CA']")
    WebElement countryCanada;


    // Click Checkout button
    public StudentInformationPage clickCheckoutButton() {
        waitForElement(driver, checkoutButton);
        clickOnElement(driver, checkoutButton);

        return new StudentInformationPage(driver);
    }

    // Confirm Cart data for 100-hour OISE TEFL course
    public void checkCartDataCourse100H() {
        isTextDisplayed(oiseTeflCourse, OISE_TEFL_COURSE_TEXT);
        isTextDisplayed(price100h, properties.getProperty(PRICE_100_HOUR_COURSE_TEXT));
        isQuantityDisplayed(quantity100h, QUANTITY_100_HOUR_COURSE_TEXT);
    }

    // Confirm Cart data for 120-hour OISE TEFL course
    public void checkCartData120h() {
        waitForElement(driver, oiseTeflCourse120h);
        isTextDisplayed(oiseTeflCourse120h, TITLE_120H);
        isTextDisplayed(price120h, properties.getProperty(PRICE_120H));
        isQuantityDisplayed(quantity120h, QUANTITY_120H);
    }

    // Confirm Cart data for 150-hour OISE TEFL course
    public void checkCartDataCourse150H() {
        isTextDisplayed(oiseCourse150h, OISE_COURSE_150H_TEXT);
        isQuantityDisplayed(quantity100h, QUANTITY_100_HOUR_COURSE_TEXT);
        isTextDisplayed(price150h, properties.getProperty(OISE_COURSE_150H_PRICE));
    }

    //Add more items
    public void clickMoreButton(int clicks) {
        int quantityValue = Integer.parseInt(QUANTITY_120H) + clicks;
        while (clicks > 0) {
            clickOnElementAndDisplayeText(driver, moreButton, MORE_BUTTON);
            clicks--;
        }
        checkUpdatedQuantity(quantityValue);
        checkUpdatedPrize(quantityValue);
    }

    //Check the quantity after clicking more button
    public void checkUpdatedQuantity(int quantityValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.attributeContains(quantity120h, "value", Integer.toString(quantityValue)));
            isQuantityDisplayed(quantity120h, Integer.toString(quantityValue));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    //Check the prize after clicking more button
    public void checkUpdatedPrize(int quantityValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String price = Float.toString((quantityValue * Float.parseFloat(properties.getProperty(PRICE_120H).substring(1, 9).replace(",", ""))));
        String priceString = "$" + price.substring(0, 1) + "," + price.substring(1, 6) + "0 USD";
        try {
            wait.until(ExpectedConditions.textToBe(By.xpath(PRICE_PATH), priceString));
            isTextDisplayed(price120h, priceString);
        } catch (TimeoutException e) {
            System.err.println("Unable to find element");
            e.printStackTrace();
        }
    }

    public CollectionsPage clickContinueShooping() {
        clickOnElementAndAssertUrl(driver, continueShopping, properties.getProperty(COLLECTIONS_URL));

        return new CollectionsPage(driver);
    }

    public void confirmSubtotalPrice() {
        isTextDisplayed(subtotalValue, properties.getProperty(SUBTOTAL_VALUE_COURSES_100H_150H));
    }

    public void changeRegionAndConfirmPrice(String region) {
        log.info("Displayed country before changing is: " + regionButton.getText());
        clickOnElement(driver, regionButton);
        clickOnElement(driver, countryCanada);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.textToBe(By.xpath(COUNTRY_XPATH), "Canada (CAD $)"));
            log.info("Displayed Country after changing is: " + regionButton.getText());
            isTextDisplayed(subtotalValue, properties.getProperty(PRICE_100_HOUR_COURSE_CANADA_TEXT));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
