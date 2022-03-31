package com.teachaway.base.grid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    public static Logger log;
    protected Properties properties = new Properties();
    public static final String GRID_SETTING = "grid";
    public static final String CONFIG_PROPERTIES_PATH = "src/main/resources/config.properties";
    public static final String TEXTSOURCE_PROPERTIES_PATH = "src/main/resources/TextSource.properties";
    public static final String CHROME_BROWSER = "chrome";
    public static final String WEB_DIRECTORY = "browser";
    public static final String CONFIG_SETTING = "setting";
    public static final String NODE_URL = "nodeURL";


    public BaseTest() {

        // Create Properties class object to access properties file
        FileInputStream configFileInput = null;
        FileInputStream textFileInput = null;
        try {
            configFileInput = new FileInputStream(new File(CONFIG_PROPERTIES_PATH));
            textFileInput = new FileInputStream(new File(TEXTSOURCE_PROPERTIES_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Load properties file
        try {
            properties.load(configFileInput);
            properties.load(textFileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({WEB_DIRECTORY, CONFIG_SETTING})
    protected void setUp(@Optional(CHROME_BROWSER) String browser, @Optional(GRID_SETTING) String setting, ITestContext ctx) throws MalformedURLException {
        // Create Driver
        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        if (setting.equals(GRID_SETTING)) {
            driver = factory.createGridDriver(properties.getProperty(NODE_URL));
        } else {
            driver = factory.createDriver();
        }

        // Maximize browser window
        driver.manage().window().maximize();

        // Set up test name and Logger
        setCurrentThreadName();
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
    }

    // waiting for Web Element to be visible
    protected void waitForElement(WebDriver driver, WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch(TimeoutException e){
            System.err.println(element + "Couldn't find element after waiting" + "\n");
            e.printStackTrace();
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element with xpath: " + element + "\n");
            e.printStackTrace();
        }
        catch(ElementNotVisibleException e){
            System.err.println(element + "is not visible" + "\n");
            e.printStackTrace();
        }
    }

    // waiting for url to change after clicking a Web Element and asserting if it redirects to the expected URL
    protected void assertUrls(WebDriver driver, String expectedUrl) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            log.info("URL verification is complete.");
        }
        catch(TimeoutException e){
            System.err.println("Could not find the required URL after waiting");
            System.err.println("Expected to be redirected to: " + expectedUrl);
            System.err.println("Instead redirected to: " + driver.getCurrentUrl() + "\n");
            e.printStackTrace();
        }
    }

    // Clicks on a Web Element that redirects to another page
    protected void clickOnElementAndAssertUrl(WebDriver driver, WebElement element, String expectedUrl) {
        System.out.println(" ");
        clickOnElement(driver, element);
        assertUrls(driver, expectedUrl);
    }

    //clicks on an element that does not redirect to another page
    protected void clickOnElement(WebDriver driver, WebElement element){
        try {
            String text = element.getText();
            clickOnElementAndDisplayeText(driver, element, text);
        }
        catch (NoSuchElementException e){
            log.info("Unable to locate element with xpath: " + element + "\n");
            e.printStackTrace();
        }
    }

    protected void clickOnElementAndDisplayeText(WebDriver driver, WebElement element, String textToBeDisplayed) {
        System.out.println(" ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked " + textToBeDisplayed + "\n");
        }
        catch (NoSuchElementException e){
            log.info("Unable to locate element with xpath: " + element + "\n");
            e.printStackTrace();
        }
        catch (ElementClickInterceptedException e){
            System.err.println(textToBeDisplayed + " can not be clicked" + "\n");
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e) {
            System.err.println(textToBeDisplayed + " is not interactable with" + "\n");
            e.printStackTrace();
        }
    }

    protected void hoverElement(WebDriver driver, WebElement element, String textDisplayed) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        try {
            js.executeScript("arguments[0].scrollIntoView();", element);
            actions.moveToElement(element).build().perform();
            log.info(textDisplayed);
        } catch (NoSuchElementException e) {
            System.err.println("Unable to locate element: " + element);
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }
    }

    protected void hoverAndClick(WebDriver driver, WebElement mainButton, WebElement subButton, String mainText, String subText) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Actions actions = new Actions(driver);
            hoverElement(driver, mainButton, "Hovered " + mainText);
            waitForElement(driver, subButton);
            hoverElement(driver, subButton, "Clicked " + subText);
            actions.click().build().perform();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
        }
    }

    // Confirm element is displayed
    protected void isElementDisplayed(WebElement element) {
        try {
            boolean DisplayElement = element.isDisplayed();
            log.info("Element displayed is: " + element + "" + DisplayElement);
        } catch (NoSuchElementException e) {
            System.err.println("Unable to find element: " + element);
        }
    }

    // Confirm text that is displayed
    protected void isTextDisplayed(WebElement element, String text) {
        try {
            String data = element.getText();
            Assert.assertEquals(data, text);
            log.info("Text verification is complete. The displayed text: " + text);
        } catch (java.lang.AssertionError e) {
            System.err.println("Displayed data is not correct");
            System.err.println("Expected to be displayed: " + text);
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.err.println("Unable to find element" + element);
            e.printStackTrace();
        }
    }

    // Confirm the number that is displayed
    protected void isQuantityDisplayed(WebElement element, String text) {
        try {
            String data = element.getAttribute("value");
            Assert.assertEquals(data, text);
            log.info("Quantity verification is complete. The displayed quantity: " + text);
        } catch (java.lang.AssertionError e) {
            System.err.println("The quantity displayed is not correct");
            System.err.println("Expected to be displayed: " + text);
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.err.println("Unable to locate element: " + element);
        }
    }

    // Select one option from the dropdown list
    protected void selectDropdownItem(WebElement element, String option) {
        try {
            Select drpElement = new Select(element);
            drpElement.selectByVisibleText(option);
            log.info("It is selected: " + option + " from: " + element.getAttribute("name"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    // Type content to an element
    protected void sendKeys(WebDriver driver, WebElement element, String data) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
            element.sendKeys(data);
            log.info("Added to the " + element.getAttribute("id") + ": " + data);
        } catch (NoSuchElementException e) {
            System.err.println("Unable to find element" + element);
        } catch (ElementNotInteractableException e) {
            System.err.println(element + "is not interactable");
        }
    }

    // Clear content to an element
    protected void clearData(WebElement element) {
        try {
            element.clear();
            log.info("Data is deleted for: " + element.getAttribute("id"));
        } catch (NoSuchElementException e) {
            System.err.println("Unable to find element" + element);
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        // Closing driver
        log.info("[Closing driver]");
        driver.quit();
    }

    // Sets thread name which includes thread id
    private void setCurrentThreadName() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        String threadId = String.valueOf(thread.getId());
        if (!threadName.contains(threadId)) {
            thread.setName(threadName + " " + threadId);
        }
    }
}
