package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeflCertificationGuidePage extends BaseTest {

    WebDriver driver;

    public TeflCertificationGuidePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/p[2]/a[1]")
    WebElement ExploreButton;

    //Click on Explore Button
    public TeflCertificationPage clickExploreButton() {
        waitForElement(driver, ExploreButton);
        clickOnElement(driver, ExploreButton);

        return new TeflCertificationPage(driver);
    }

}
