package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OisePage extends BaseTest {

    WebDriver driver;
    public static final String OISE_TEFL_URL = "oiseTeflURL";


    public OisePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body[1]/main[1]/div[1]/header[1]/div[1]/div[2]/a[1]")
    WebElement enrollNowButton;

    public EnrollPage clickEnrollNowButton() {
        waitForElement(driver, enrollNowButton);
        clickOnElementAndAssertUrl(driver, enrollNowButton, properties.getProperty(OISE_TEFL_URL));

        return new EnrollPage(driver);
    }
}
