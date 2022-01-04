package com.teachaway.base.grid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Logger log;
    protected Properties prop = new Properties();

    public BaseTest() {
//        create Properties class object to access properties file
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(new File("src/main/resources/config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "setting"})
    protected void setUp(@Optional("chrome") String browser, @Optional("grid") String setting, ITestContext ctx) throws MalformedURLException {
//        Create Driver
        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        if (setting.equals("grid")) {
            driver = factory.createDriverGrid();
        } else {
            driver = factory.createDriver();
        }

//        maximize browser window
        driver.manage().window().maximize();
//        get site URL
        driver.get(prop.getProperty("url"));

//        Set up test name and Logger
        setCurrentThreadName();
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
//        Closing driver
        log.info("[Closing driver]");
        driver.quit();
    }

    //        Sets thread name which includes thread id
    private void setCurrentThreadName() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        String threadId = String.valueOf(thread.getId());
        if (!threadName.contains(threadId)) {
            thread.setName(threadName + " " + threadId);
        }
    }
}

