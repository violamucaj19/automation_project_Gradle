package com.teachaway.test.cases;

import com.teachaway.base.grid.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NavigateTest extends BaseTest {

    @Parameters({WEB_DIRECTORY, CONFIG_SETTING})
    @Test
    public void init() throws Exception {
        String expectedUrl = properties.getProperty(CONFIG_KEY_URL);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}

