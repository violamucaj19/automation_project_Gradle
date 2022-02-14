package com.teachaway.test.cases;

import com.teachaway.pages.Footer;
import com.teachaway.base.grid.BaseTest;
import org.testng.annotations.Test;

public class FooterWithoutNavigateBackTest extends BaseTest {
    public static final String TEFL_ONLINE_URL = "teflOnlineUrl";

    //Tests the links in TeflOnline footer and its functionalities without going back to TeflOnline homepage
    @Test
    public void footerTestCases() {
        Footer footerPageTest = new Footer(driver);

        String homeUrl = properties.getProperty(TEFL_ONLINE_URL);
        driver.get(homeUrl);

        footerPageTest.clickOnAcceptButton();

        footerPageTest.clickOnEnroll();

        footerPageTest.clickOnLiveInfoSession();

        footerPageTest.clickOnTeflBlog();

        footerPageTest.clickOnTeflBrochure();

        footerPageTest.clickOnTeflTraining();

        footerPageTest.clickOnCourseFees();

        footerPageTest.clickOnRefundPolicy();

        footerPageTest.clickOnTeflCertification();

        footerPageTest.clickOnContactUs();

        footerPageTest.checkForTeachawayLogo();

        footerPageTest.changeCountry();

        footerPageTest.clickOnStudentLogin();

    }

}
