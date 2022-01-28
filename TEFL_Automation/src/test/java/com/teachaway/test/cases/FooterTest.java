package com.teachaway.test.cases;

import com.teachaway.pages.Footer;
import com.teachaway.base.grid.BaseTest;
import org.testng.annotations.Test;

public class FooterTest extends BaseTest {

    @Test
    public void init() {
        Footer footerPageCheck = new Footer(driver);

        String homeUrl = properties.getProperty(TEFL_ONLINE_URL);
        driver.get(homeUrl);

        footerPageCheck.clickOnAcceptBtn();

        footerPageCheck.clickOnEnroll();
        driver.get(homeUrl);

        footerPageCheck.clickOnLiveInfoSession();
        driver.get(homeUrl);

        footerPageCheck.clickOnTeflBlog();
        driver.get(homeUrl);

        footerPageCheck.clickOnTeflBrochure();
        driver.get(homeUrl);

        footerPageCheck.clickOnTeflTraining();
        driver.get(homeUrl);

        footerPageCheck.clickOnStudentLogin();
        driver.get(homeUrl);

        footerPageCheck.clickOnCourseFees();
        driver.get(homeUrl);

        footerPageCheck.clickOnRefundPolicy();
        driver.get(homeUrl);

        footerPageCheck.clickOnTeflCertification();
        driver.get(homeUrl);

        footerPageCheck.clickOnContactUs();
        driver.get(homeUrl);

        footerPageCheck.checkForTeachawayLogo();

        footerPageCheck.changeCountryToCanada();

        footerPageCheck.subscribeToEmails();

    }

}

