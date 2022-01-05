package com.teachaway.base.grid;

import com.teachaway.utility.Constant;
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

public class browserDriverFactory {

    //    Driver Setup
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;

    public browserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public static String getDriverPath(String browser) {
        String OS = System.getProperty("os.name");

        if (OS.contains(Constant.WindowOP)) {
            return browser + ".exe";
        }
        return browser;
    }

    public WebDriver createDriver() {
        System.out.println("Starting " + browser + " locally");

//        Creating driver locally
        switch (browser) {
            case "chrome":
                System.setProperty(Constant.ChromeProperties, getDriverPath(Constant.ChromeDriver));
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty(Constant.FoxProperties, getDriverPath(Constant.FoxDriver));
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty(Constant.EdgeProperties, getDriverPath(Constant.EdgeDriver));
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
        driver.set(new RemoteWebDriver(new URL(Constant.nodeURL), (Capabilities) option));

        return (Capabilities) option;
    }

    public WebDriver createGridDriver() throws MalformedURLException {

//        Creating driver on grid
        switch (browser) {
            case "chrome":
                settingOptions(Constant.ChromeBrowser, Constant.ChromeCapability, (Capabilities) new ChromeOptions());
                break;
            case "firefox":
                settingOptions(Constant.FoxBrowser, Constant.FoxCapability, (Capabilities) new FirefoxOptions());
                break;
            case "edge":
                settingOptions(Constant.EdgeBrowser, Constant.EdgeCapability, (Capabilities) new EdgeOptions());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        return driver.get();
    }
}
