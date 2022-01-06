package com.teachaway.base.grid;

import org.apache.commons.text.WordUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserDriverFactory extends BaseTest {

    public static final String CHROME_DRIVER = "chromedriver";
    public static final String CHROME_PROPERTIES = "webdriver.chrome.driver";
    public static final String CHROME_CAPABILITY = "ChromeOptions.CAPABILITY";
    public static final String FIREFOX_DRIVER = "geckodriver";
    public static final String FIREFOX_PROPERTIES = "webdriver.gecko.driver";
    public static final String FIREFOX_BROWSER = "firefox";
    public static final String FIREFOX_CAPABILITY = "FirefoxOptions.FIREFOX_OPTIONS";
    public static final String EDGE_DRIVER = "msedgedriver";
    public static final String EDGE_PROPERTIES = "webdriver.msedge.driver";
    public static final String EDGE_BROWSER = "edge";
    public static final String EDGE_CAPABILITY = "EdgeOptions.CAPABILITY";
    public static final String WINDOW_OP = "Window";
    public static final String NODE_URL = "nodeURL";

    //Driver Setup
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public static String getDriverPath(String browser) {
        String OS = System.getProperty("os.name");

        if (OS.contains(WINDOW_OP)) {
            return browser + ".exe";
        }
        return browser;
    }

    public WebDriver createDriver() {
        System.out.println("Starting " + browser + " locally");

        //Creating driver locally
        switch (browser) {
            case CHROME_BROWSER:
                System.setProperty(CHROME_PROPERTIES, getDriverPath(CHROME_DRIVER));
                driver.set(new ChromeDriver());
                break;
            case FIREFOX_BROWSER:
                System.setProperty(FIREFOX_PROPERTIES, getDriverPath(FIREFOX_DRIVER));
                driver.set(new FirefoxDriver());
                break;
            case EDGE_BROWSER:
                System.setProperty(EDGE_PROPERTIES, getDriverPath(EDGE_DRIVER));
                driver.set(new EdgeDriver());
                break;
        }

        return driver.get();
    }

    //Create Options
    public Capabilities settingOptions(String browserName, String capability, Capabilities option) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.out.println("Starting " + browser + " on grid");
        capabilities.setCapability(capability, option);
        capabilities.setBrowserName(browserName);
        driver.set(new RemoteWebDriver(new URL(properties.getProperty(NODE_URL)), (Capabilities) option));

        return (Capabilities) option;
    }

    public WebDriver createGridDriver() throws MalformedURLException {

        //Creating driver on grid
        switch (browser) {
            case CHROME_BROWSER:
                settingOptions(WordUtils.capitalize(CHROME_BROWSER), CHROME_CAPABILITY, (Capabilities) new ChromeOptions());
                break;
            case FIREFOX_BROWSER:
                settingOptions(WordUtils.capitalize(FIREFOX_BROWSER), FIREFOX_CAPABILITY, (Capabilities) new FirefoxOptions());
                break;
            case EDGE_BROWSER:
                settingOptions(WordUtils.capitalize(EDGE_BROWSER), EDGE_CAPABILITY, (Capabilities) new EdgeOptions());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        return driver.get();
    }
}
