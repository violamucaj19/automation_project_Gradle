package com.teachaway.pages;

import com.teachaway.base.grid.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Footer extends BaseTest {
    WebDriver driver;

    public static final String COURSES_URL = "coursesUrl";
    public static final String LIVE_INFO_SESSION_URL = "liveInfoSessionUrl";
    public static final String TEFL_BLOG_URL = "teflBlogUrl";
    public static final String TEFL_BROCHURE_URL = "teflBrochureUrl";
    public static final String TEFL_TRAINING_URL = "teflTrainingUrl";
    public static final String STUDENT_LOGIN_URL = "studentLoginUrl";
    public static final String REFUND_POLICY_URL = "refundPolicyUrl";
    public static final String CONTACT_URL = "contactUrl";
    public static final String COUNTRY_XPATH = "//body//div[@id='shopify-section-footer']//button[@aria-describedby='FooterCountryLabel']";

    public Footer (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //finding all elements

    @FindBy(id = "hs-eu-confirmation-button")
    WebElement acceptButton;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'Enroll')]")
    WebElement enrollLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'Live Info Session')]")
    WebElement liveInfoSessionLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'TEFL Blog')]")
    WebElement teflBlogLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'TEFL Online Program Brochure')]")
    WebElement teflBrochureLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'TEFL Training for Institutions')]")
    WebElement teflTrainingLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'Student Login')]")
    WebElement studentLoginLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'Course Fees')]")
    WebElement courseFeesLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'Refund Policy')]")
    WebElement refundPolicyLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'TEFL certification')]")
    WebElement teflCertificationLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[contains(text(),'Contact Us')]")
    WebElement contactUsLink;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//button[@aria-describedby='FooterCountryLabel']")
    WebElement countryDropdown;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//a[@data-value='CA']")
    WebElement countryCanada;

    @FindBy(xpath = "//body//div[@id='shopify-section-footer']//img[@alt='']")
    WebElement teachawayLogo;

    public void clickOnAcceptButton() {
        clickOnElement(driver, acceptButton);
    }

    public void clickOnEnroll() {
        clickOnElementAndAssertUrl(driver, enrollLink, properties.getProperty(COURSES_URL));
    }

    public void clickOnLiveInfoSession() {
        clickOnElementAndAssertUrl(driver, liveInfoSessionLink, properties.getProperty(LIVE_INFO_SESSION_URL));
    }

    public void clickOnTeflBlog() {
        clickOnElementAndAssertUrl(driver, teflBlogLink, properties.getProperty(TEFL_BLOG_URL));
    }

    public void clickOnTeflBrochure() {
        clickOnElementAndAssertUrl(driver, teflBrochureLink, properties.getProperty(TEFL_BROCHURE_URL));
    }

    public void clickOnTeflTraining() {
        clickOnElementAndAssertUrl(driver, teflTrainingLink, properties.getProperty(TEFL_TRAINING_URL));
    }

    public void clickOnStudentLogin() {
        clickOnElementAndAssertUrl(driver, studentLoginLink, properties.getProperty(STUDENT_LOGIN_URL));
    }

    public void clickOnCourseFees() {
        clickOnElementAndAssertUrl(driver, courseFeesLink, properties.getProperty(COURSES_URL));
    }

    public void clickOnRefundPolicy() {
        clickOnElementAndAssertUrl(driver, refundPolicyLink, properties.getProperty(REFUND_POLICY_URL));
    }

    public void clickOnTeflCertification(){
        clickOnElementAndAssertUrl(driver, teflCertificationLink, properties.getProperty(COURSES_URL));
    }

    public void clickOnContactUs(){
        clickOnElementAndAssertUrl(driver, contactUsLink, properties.getProperty(CONTACT_URL));
    }

    //Checks if Teach Away logo is displayed in the footer
    public void checkForTeachawayLogo(){
        try {
            if (teachawayLogo.isDisplayed()) {
                System.out.println(" ");
                log.info("Teach Away logo is visible");
            }
            else
            System.err.println("Teach Away logo is not visible");
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element" + teachawayLogo);
            e.printStackTrace();
        }
    }

    //checks if the user can change the country on the country dropdown on the footer
    public void changeCountry(){
        System.out.println(" ");
        log.info("Displayed country before changing is: " + countryDropdown.getText());
        clickOnElement(driver, countryDropdown);
        clickOnElement(driver, countryCanada);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.textToBe(By.xpath(COUNTRY_XPATH), "Canada (CAD $)"));
            log.info("Displayed Country after changing is: " + countryDropdown.getText());
        }
        catch (TimeoutException e){
            e.printStackTrace();
        }
    }

}
