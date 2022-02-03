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
    public static final String EMAIL = "edisa@skooli.com";

    public Footer (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //finding all elements

    @FindBy(id = "hs-eu-confirmation-button")
    WebElement acceptBtn;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[1]/a[1]")
    WebElement enrollLink;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[2]/a[1]")
    WebElement liveInfoSessionLink;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[3]/a[1]")
    WebElement teflBlogLink;

    @FindBy(xpath = "//a[contains(text(),'TEFL Online Program Brochure')]")
    WebElement teflBrochureLink;

    @FindBy(xpath = "//a[contains(text(),'TEFL Training for Institutions')]")
    WebElement teflTrainingLink;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[6]/a[1]")
    WebElement studentLoginLink;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[7]/a[1]")
    WebElement courseFeesLink;

    @FindBy(xpath = "//a[contains(text(),'Refund Policy')]")
    WebElement refundPolicyLink;

    @FindBy(xpath = "//body[1]/div[3]/footer[1]/div[1]/div[1]/div[1]/div[1]/p[1]/a[1]")
    WebElement teflCertificationLink;

    @FindBy(xpath = "//body[1]/div[3]/footer[1]/div[1]/div[1]/div[3]/div[1]/p[1]/a[2]")
    WebElement contactUsLink;

    @FindBy(xpath = "//body[1]/div[3]/footer[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/input[1]")
    WebElement emailInput;

    @FindBy(xpath = "//body[1]/div[3]/footer[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/button[1]")
    WebElement submitButton;

    @FindBy(xpath = "//body[1]/div[3]/footer[1]/div[1]/div[2]/div[1]/form[1]/h3[1]")
    WebElement subscribeMessage;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[2]/div[1]/div[1]/localization-form[1]/form[1]/div[1]/div[1]/button[1]")
    WebElement countryDropdown;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[2]/div[1]/div[1]/localization-form[1]/form[1]/div[1]/div[1]/ul[1]/li[38]/a[1]")
    WebElement countryCanada;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[1]/div[1]/div[1]/img[1]")
    WebElement teachawayLogo;

    public void clickOnAcceptBtn() {
        clickOnElement(driver, acceptBtn);
    }

    public void clickOnEnroll() {
        clickOnElement(driver, enrollLink, properties.getProperty(COURSES_URL), "/enroll");
    }

    public void clickOnLiveInfoSession() {
        clickOnElement(driver, liveInfoSessionLink, properties.getProperty(LIVE_INFO_SESSION_URL), "/information-session");
    }

    public void clickOnTeflBlog() {
        clickOnElement(driver, teflBlogLink, properties.getProperty(TEFL_BLOG_URL), "/tefl-resources");
    }

    public void clickOnTeflBrochure() {
        clickOnElement(driver, teflBrochureLink, properties.getProperty(TEFL_BROCHURE_URL), "/tefl-online-program-brochure");
    }

    public void clickOnTeflTraining() {
        clickOnElement(driver, teflTrainingLink, properties.getProperty(TEFL_TRAINING_URL), "/tefl-training-for-institutions");
    }

    public void clickOnStudentLogin() {
        clickOnElement(driver, studentLoginLink, properties.getProperty(STUDENT_LOGIN_URL), "/login");
    }

    public void clickOnCourseFees() {
        clickOnElement(driver, courseFeesLink, properties.getProperty(COURSES_URL), "/enroll");
    }

    public void clickOnRefundPolicy() {
        clickOnElement(driver, refundPolicyLink, properties.getProperty(REFUND_POLICY_URL), "/refund-policy");
    }

    public void clickOnTeflCertification(){
        clickOnElement(driver, teflCertificationLink, properties.getProperty(COURSES_URL), "/enroll");
    }

    public void clickOnContactUs(){
        clickOnElement(driver, contactUsLink, properties.getProperty(CONTACT_URL), "/contact");
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
            wait.until(ExpectedConditions.textToBe(By.xpath("//body/div[@id='shopify-section-footer']/footer[1]/div[2]/div[1]/div[1]/localization-form[1]/form[1]/div[1]/div[1]/button[1]"), "Canada (CAD $)"));
            log.info("Displayed Country after changing is: " + countryDropdown.getText());
        }
        catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    //checks if subscription to the emails is successful
    public void subscribeToEmails(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", emailInput);
            emailInput.sendKeys(EMAIL);
            System.out.println(" ");
            log.info("Entered email address");
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element" + emailInput);
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e){
            System.err.println(emailInput + " is not interactable with");
            e.printStackTrace();
        }
        clickOnElement(driver, submitButton);
        try {
            waitForElement(driver, subscribeMessage);
            log.info(subscribeMessage.getText());
        }
        catch (NoSuchElementException e){
            System.err.println("Subscription was not successful!! Unable to locate element" + subscribeMessage);
            e.printStackTrace();
        }
    }

}
