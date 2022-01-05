package com.teachaway.base.grid;

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

    public static final String CHROMEDRIVER = "chromedriver";
    public static final String CHROMEPROPERTIES = "webdriver.chrome.driver";
    public static final String CHROMEBROWSER = "Chrome";
    public static final String CHROMECAPABILITY = "ChromeOptions.CAPABILITY";
    public static final String FOXDRIVER = "geckodriver";
    public static final String FOXPROPERTIES = "webdriver.gecko.driver";
    public static final String FOXBROWSER = "Firefox";
    public static final String FOXCAPABILITY = "FirefoxOptions.FIREFOX_OPTIONS";
    public static final String EDGEDRIVER = "msedgedriver";
    public static final String EDGEPROPERTIES = "webdriver.msedge.driver";
    public static final String EDGEBROWSER = "Edge";
    public static final String EDGECAPABILITY = "EdgeOptions.CAPABILITY";
    public static final String WINDOWOP = "Window";

    //    Driver Setup
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public static String getDriverPath(String browser) {
        String OS = System.getProperty("os.name");

        if (OS.contains(WINDOWOP)) {
            return browser + ".exe";
        }
        return browser;
    }

    public WebDriver createDriver() {
        System.out.println("Starting " + browser + " locally");

//        Creating driver locally
        switch (browser) {
            case "chrome":
                System.setProperty(CHROMEPROPERTIES, getDriverPath(CHROMEDRIVER));
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty(FOXPROPERTIES, getDriverPath(FOXDRIVER));
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty(EDGEPROPERTIES, getDriverPath(EDGEDRIVER));
                driver.set(new EdgeDriver());
                break;
        }

        return driver.get();
    }

    //    Create Options
    public Capabilities settingOptions(String browserName, String capability, Capabilities option) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.out.println("Starting " + browser + " on grid");
        capabilities.setCapability(capability, option);
        capabilities.setBrowserName(browserName);
        driver.set(new RemoteWebDriver(new URL(properties.getProperty("nodeURL")), (Capabilities) option));

        return (Capabilities) option;
    }

    public WebDriver createGridDriver() throws MalformedURLException {

//        Creating driver on grid
        switch (browser) {
            case "chrome":
                settingOptions(CHROMEBROWSER, CHROMECAPABILITY, (Capabilities) new ChromeOptions());
                break;
            case "firefox":
                settingOptions(FOXBROWSER, FOXCAPABILITY, (Capabilities) new FirefoxOptions());
                break;
            case "edge":
                settingOptions(EDGEBROWSER, EDGECAPABILITY, (Capabilities) new EdgeOptions());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        return driver.get();
    }
}
