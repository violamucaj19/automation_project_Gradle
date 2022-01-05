package com.teachaway.test.cases;

import com.teachaway.base.grid.baseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.teachaway.utility.Constant;


public class NavigateTest extends baseTest {

    @Parameters({"browser", "environment"})
    @Test
    public void init() throws Exception {
        String expectedUrl = Constant.url;
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }
}

