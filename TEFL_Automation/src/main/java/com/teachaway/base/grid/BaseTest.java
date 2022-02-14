package com.teachaway.base.grid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public static final String PROPERTIES_PATH = "src/main/resources/config.properties";
    public static final String CHROME_BROWSER = "chrome";
    public static final String WEB_DIRECTORY = "browser";
    public static final String CONFIG_SETTING = "setting";
    public static final String NODE_URL = "nodeURL";


    public BaseTest() {

        // Create Properties class object to access properties file
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(new File(PROPERTIES_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Load properties file
        try {
            properties.load(fileInput);
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
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch(TimeoutException e){
            System.err.println(element + "Couldn't find element after waiting");
            e.printStackTrace();
        }
        catch (NoSuchElementException e){
            System.err.println("Unable to locate element with xpath: " + element);
            e.printStackTrace();
        }
        catch(ElementNotVisibleException e){
            System.err.println(element + "is not visible");
            e.printStackTrace();
        }
    }

    // waiting for url to change after clicking a Web Element and asserting if it redirects to the expected URL
    protected void assertUrls(WebDriver driver, String expectedUrl){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            log.info("URL verification is complete.");
        }
        catch(TimeoutException e){
            System.err.println("Could not find the required URL after waiting");
            System.err.println("Expected to be redirected to: " + expectedUrl);
            System.err.println("Instead redirected to: " + driver.getCurrentUrl());
            e.printStackTrace();
        }
    }

    // Clicks on a Web Element that redirects to another page
    protected void clickOnElementAndAssertUrl(WebDriver driver, WebElement element, String expectedUrl){
        System.out.println(" ");
        clickOnElement(driver, element);
        assertUrls(driver, expectedUrl);
    }

    //clicks on an element that does not redirect to another page
    protected void clickOnElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            String text = element.getText();
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked " + text);
        }
        catch (NoSuchElementException e){
            log.info("Unable to locate element with xpath: " + element);
            e.printStackTrace();
        }
        catch (ElementClickInterceptedException e){
            System.err.println(element.getText() + " can not be clicked");
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e){
            System.err.println(element.getText() + " is not interactable with");
            e.printStackTrace();
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

