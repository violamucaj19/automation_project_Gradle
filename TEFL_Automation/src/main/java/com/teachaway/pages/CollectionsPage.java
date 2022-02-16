package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollectionsPage extends BaseTest {

    public static final String OISE_TEFL_COURSE_100H = "oiseTeflCourse100HURL";

    public CollectionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'100-hour OISE TEFL course')]")
    WebElement teflCourse100hProduct;

    public TeflOiseCourse100HPage selectOiseCourse100H() {
        waitForElement(driver, teflCourse100hProduct);
        clickOnElementAndAssertUrl(driver, teflCourse100hProduct, properties.getProperty(OISE_TEFL_COURSE_100H));

        return new TeflOiseCourse100HPage(driver);
    }
}
