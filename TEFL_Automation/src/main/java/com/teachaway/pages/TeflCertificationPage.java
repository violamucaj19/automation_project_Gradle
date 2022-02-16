package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeflCertificationPage extends BaseTest {

    WebDriver driver;
    public static final String ENROLL_URL = "enrollURL";
    public static final String OISE_URL = "oiseTeflURL";

    public TeflCertificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/main[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/a[2]")
    WebElement EnrollButton;

    @FindBy(id = "hs-eu-confirmation-button")
    WebElement ConfirmButton;

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/main[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]")
    WebElement Banner;

    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/main[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/a[1]")
    WebElement GetStartedBtn;

    // Confirm cookies
    public void confirmCookies() {
        waitForElement(driver, ConfirmButton);
        clickOnElement(driver, ConfirmButton);
    }

    // Click on Enroll Button
    public EnrollPage clickEnrollButton() {
        waitForElement(driver, EnrollButton);
        clickOnElementAndAssertUrl(driver, EnrollButton, properties.getProperty(ENROLL_URL));

        return new EnrollPage(driver);
    }

    public OisePage clickGetStartedButton() {
        waitForElement(driver, GetStartedBtn);
        clickOnElementAndAssertUrl(driver, GetStartedBtn, properties.getProperty(OISE_URL));

        return new OisePage(driver);
    }
}
