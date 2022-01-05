package com.teachaway.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Pages {
    WebDriver driver;

    public Pages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
