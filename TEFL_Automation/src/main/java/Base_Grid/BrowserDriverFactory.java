package Base_Grid;

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

public class BrowserDriverFactory {
    /**
     * Driver Setup
     */

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    BaseTest object = new BaseTest();

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }


    public WebDriver createDriver() {
        System.out.println("Starting " + browser + " locally");

        /** Creating driver locally*/
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver.set(new FirefoxDriver());
                break;

            case "edge":
                System.setProperty("webdriver.msedge.driver", "src/main/resources/msedgedriver");
                driver.set(new EdgeDriver());
                break;
        }

        return driver.get();
    }

    public WebDriver createDriverGrid() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.out.println("Starting " + browser + " on grid");

        /** Creating driver on grid*/
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setBrowserName("Chrome");
                chromeOptions.merge(capabilities);
                driver.set(new RemoteWebDriver(new URL(object.prop.getProperty("nodeURL")), chromeOptions));
                break;
            case "firefox":
                FirefoxOptions foxOptions = new FirefoxOptions();
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, foxOptions);
                capabilities.setBrowserName("Firefox");
                foxOptions.merge(capabilities);
                driver.set(new RemoteWebDriver(new URL(object.prop.getProperty("nodeURL")), foxOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
                capabilities.setBrowserName("Edge");
                edgeOptions.merge(capabilities);
                driver.set(new RemoteWebDriver(new URL(object.prop.getProperty("nodeURL")), edgeOptions));
        }

        return driver.get();
    }
}

