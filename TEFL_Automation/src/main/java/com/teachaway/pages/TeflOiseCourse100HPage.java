package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeflOiseCourse100HPage extends BaseTest {
    public static final String CART_URL = "cartUrl";

    public TeflOiseCourse100HPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='product-form__buttons']//button[@type='submit']")
    WebElement addCartButton;

    public CartPage clickAddCartButton() {
        clickOnElementAndAssertUrl(driver, addCartButton, properties.getProperty(CART_URL));

        return new CartPage(driver);
    }
}
