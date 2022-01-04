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

    //        Driver Setup
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public static String getDriverPath(String browser) {
        String OS = System.getProperty("os.name");

        if (OS.contains("Window")) {
            return browser + ".exe";
        } else {
            return browser;
        }
    }

    public WebDriver createDriver() {
        System.out.println("Starting " + browser + " locally");

//        Creating driver locally
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", getDriverPath("chromedriver"));
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", getDriverPath("geckodriver"));
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty("webdriver.msedge.driver", getDriverPath("msedgedriver"));
                driver.set(new EdgeDriver());
                break;
        }
        return driver.get();
    }

    //            Create Options
    public Capabilities settingOptions(String browserName, String capability, Capabilities option) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.out.println("Starting " + browser + " on grid");
        capabilities.setCapability(capability, option);
        capabilities.setBrowserName(browserName);
        driver.set(new RemoteWebDriver(new URL(prop.getProperty("nodeURL")), (Capabilities) option));
        return (Capabilities) option;
    }

    public WebDriver createDriverGrid() throws MalformedURLException {

//        Creating driver on grid
        switch (browser) {
            case "chrome":
                settingOptions("Chrome", "ChromeOptions.CAPABILITY", (Capabilities) new ChromeOptions());
                break;
            case "firefox":
                settingOptions("Firefox", "FirefoxOptions.FIREFOX_OPTIONS", (Capabilities) new FirefoxOptions());
                break;
            case "edge":
                settingOptions("Edge", "EdgeOptions.CAPABILITY", (Capabilities) new EdgeOptions());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
        return driver.get();
    }
}
