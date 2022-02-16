package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseTest {
    WebDriver driver;
    public static final String ADD_CART_BUTTON = "Add to Cart Button";

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@name='add']")
    WebElement AddButton;

    public CartPage clickAddButton() {
        waitForElement(driver, AddButton);
        clickOnElementAndDisplayeText(driver, AddButton, ADD_CART_BUTTON);

        return new CartPage(driver);
    }
}

