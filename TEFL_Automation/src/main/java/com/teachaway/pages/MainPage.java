package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseTest {

    WebDriver driver;
    public static final String TEFL_CERTIFICATION_URL = "certificationURL";
    public static final String TEFL_CERTIFICATION_GUIDE_URL = "teflCertificationGuideURL";
    public static final String TEFL_BUTTON = "Tefl Button";
    public static final String TEFL_CERTIFICATION_TEXT = "Clicked TEFL Courses";
    public static final String TEFL_CERTIFICATION_GUIDE = "Tefl Certification Guide Button";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[1]/nav[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]")
    WebElement teflButton;

    @FindBy(xpath = "//a[contains(text(),'TEFL Courses')]")
    WebElement teflCourseButton;

    @FindBy(xpath = "//a[contains(text(),'TEFL Certification Guide')]")
    WebElement teflCertificationGuide;

    // Click Tefl Courses
    public TeflCertificationPage clickTeflButton() {
        hoverAndClick(driver, teflButton, teflCourseButton, TEFL_BUTTON, TEFL_CERTIFICATION_TEXT);
        assertUrls(driver, properties.getProperty(TEFL_CERTIFICATION_URL));

        return new TeflCertificationPage(driver);
    }

    //Click Tefl Certification Guide
    public TeflCertificationGuidePage clickTeflGuide() {
        hoverAndClick(driver, teflButton, teflCertificationGuide, TEFL_BUTTON, TEFL_CERTIFICATION_GUIDE);
        assertUrls(driver, properties.getProperty(TEFL_CERTIFICATION_GUIDE_URL));

        return new TeflCertificationGuidePage(driver);
    }
}

