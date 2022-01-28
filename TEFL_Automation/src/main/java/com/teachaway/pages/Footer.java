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

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[2]/div[1]/div[1]/localization-form[1]/form[1]/div[1]/div[1]/button[1]\n")
    WebElement countryDropdown;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[2]/div[1]/div[1]/localization-form[1]/form[1]/div[1]/div[1]/ul[1]/li[38]/a[1]")
    WebElement countryCanada;

    @FindBy(xpath = "//body/div[@id='shopify-section-footer']/footer[1]/div[1]/div[1]/div[1]/div[1]/div[1]/img[1]")
    WebElement teachawayLogo;

    public void clickOnAcceptBtn() {
        try{
            waitForElement(driver, acceptBtn);
            acceptBtn.click();
        }
        catch (NoSuchElementException e){
            System.err.println("\n" + "Unable to locate element " + acceptBtn);
        }
        catch (ElementClickInterceptedException e){
            System.err.println("\n" + acceptBtn + " is not clickable");
        }
        catch (ElementNotInteractableException e){
            System.err.println("\n" + acceptBtn + " is not interactable");
        }
    }

    public void clickOnEnroll() {
        clickOnElement(driver, enrollLink, "Enroll", "https://teflonline.teachaway.com/pages/enroll", "/enroll");
    }

    public void clickOnLiveInfoSession() {
        clickOnElement(driver, liveInfoSessionLink, "Live Info Session", "https://teflonline.teachaway.com/pages/information-session", "/information-session");
    }

    public void clickOnTeflBlog() {
        clickOnElement(driver, teflBlogLink, "TEFL Blog", "https://teflonline.teachaway.com/blogs/tefl-resources", "/tefl-resources");
    }

    public void clickOnTeflBrochure() {
        clickOnElement(driver, teflBrochureLink, "TEFL Online Program Brochure", "https://teflonline.teachaway.com/pages/tefl-online-program-brochure", "/tefl-online-program-brochure");
    }

    public void clickOnTeflTraining() {
        clickOnElement(driver, teflTrainingLink, "TEFL Training for Institutions", "https://teflonline.teachaway.com/pages/tefl-training-for-institutions", "/tefl-training-for-institutions");
    }

    public void clickOnStudentLogin() {
        clickOnElement(driver, studentLoginLink, "Student Login", "https://lms.teachaway.com/account/login", "/login");
    }

    public void clickOnCourseFees() {
        clickOnElement(driver, courseFeesLink, "Course Fees", "https://teflonline.teachaway.com/pages/enroll", "/enroll");
    }

    public void clickOnRefundPolicy() {
        clickOnElement(driver, refundPolicyLink, "Refund Policy", "https://teflonline.teachaway.com/pages/refund-policy", "/refund-policy");
    }

    public void clickOnTeflCertification(){
        clickOnElement(driver, teflCertificationLink, "TEFL certification", "https://teflonline.teachaway.com/pages/enroll", "/enroll");
    }

    public void clickOnContactUs(){
        clickOnElement(driver, contactUsLink, "Contact Us", "https://teflonline.teachaway.com/pages/contact", "/contact");
    }

    public void checkForTeachawayLogo(){
        try {
            if (teachawayLogo.isDisplayed())
                System.out.println("\n" + "Teach Away logo is visible");
            else
                System.err.println("\n" + "Teach Away logo is not visible");
        }
        catch (NoSuchElementException e){
            System.err.println("\n" + "Unable to locate element" + teachawayLogo);
        }
    }

    public void changeCountryToCanada(){
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            try {
                js.executeScript("arguments[0].scrollIntoView(true);", countryDropdown);
                waitForElement(driver, countryDropdown);
                System.out.println("\n" + "Displayed country before changing is: " + countryDropdown.getText());
                js.executeScript("arguments[0].click();", countryDropdown);
            }
            catch(NoSuchElementException e){
                System.err.println("\n" + "Unable to locate element " + countryDropdown);
            }
            catch (ElementClickInterceptedException e){
                System.err.println("\n" + countryDropdown + " is not clickable");
            }
            catch (ElementNotInteractableException e){
                System.err.println("\n" + countryDropdown + " is not interactable");
            }
            try {
                js.executeScript("arguments[0].scrollIntoView(true);", countryCanada);
                waitForElement(driver, countryCanada);
                js.executeScript("arguments[0].click();", countryCanada);
                wait.until(ExpectedConditions.textToBe(By.xpath("//body/div[@id='shopify-section-footer']/footer[1]/div[2]/div[1]/div[1]/localization-form[1]/form[1]/div[1]/div[1]/button[1]\n"), "Canada (CAD $)"));
                System.out.println("Displayed Country after changing is: " + countryDropdown.getText());
            }
            catch(NoSuchElementException e){
                System.err.println("\n" + "Unable to locate element " + countryCanada);
            }
            catch (ElementClickInterceptedException e){
                System.err.println("\n" + countryCanada + " is not clickable");
            }
            catch (ElementNotInteractableException e){
                System.err.println("\n" + countryCanada + " is not interactable");
            }
    }

    public void subscribeToEmails(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", emailInput);
            emailInput.sendKeys("edisa@skooli.com");
            System.out.println(" ");
            log.info("Entered email address");
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element" + emailInput);
        }
        catch (ElementClickInterceptedException e){
            System.err.println("\n" + emailInput + " is not clickable");
        }
        catch (ElementNotInteractableException e){
            System.err.println("\n" + emailInput + " is not interactable");
        }
        try {
            js.executeScript("arguments[0].click();", submitButton);
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element" + submitButton);
        }
        catch (ElementClickInterceptedException e){
            System.err.println("\n" + submitButton + " is not clickable");
        }
        catch (ElementNotInteractableException e){
            System.err.println("\n" + submitButton + " is not interactable");
        }
        try {
            waitForElement(driver, subscribeMessage);
            System.out.println(subscribeMessage.getText());
        }
        catch (NoSuchElementException e){
            System.err.println("Subscription was not successful!! Unable to locate element" + subscribeMessage);
        }
    }

}
