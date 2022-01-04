package com.teachaway.test.cases;

import com.teachaway.base.grid.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NavigateTest extends BaseTest {

    @Parameters({"browser", "environment"})
    @Test
    public void init() throws Exception {
        String expectedUrl = prop.getProperty("url");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}

