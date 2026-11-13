
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AffiliatePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC06_AddAffiliate extends BaseClass {

    @Test(
        groups = {"regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testAddAffiliate() throws Exception {

        log.info("========== Add Affiliate Test Started ==========");

        try {

            // Step 1: Navigate to application home page
            log.debug("Navigating to application home page");

            getDriver().get(p.getProperty("appURL"));

            log.info("Application home page loaded successfully");

            // Step 2: Initialize HomePage
            log.debug("Initializing HomePage object");

            HomePage hp = new HomePage(getDriver());

            // Step 3: Navigate to Login Page
            log.debug("Clicking My Account menu");

            hp.clickMyAccount();

            log.info("My Account menu clicked successfully");

            log.debug("Navigating to Login page");

            hp.goToLogin();

            log.info("Login page opened successfully");

            // Step 4: Initialize LoginPage
            log.debug("Initializing LoginPage object");

            LoginPage lp = new LoginPage(getDriver());

            // Step 5: Enter Login Credentials
            log.debug("Entering email address");

            lp.setEmail("krishnarajelakiya@gmail.com");

            log.info("Email entered successfully");

            log.debug("Entering password");

            lp.setPwd("test111");

            log.info("Password entered successfully");

            // Step 6: Click Login
            log.debug("Clicking Login button");

            lp.clickLogin();

            log.info("Login button clicked successfully");

            // Step 7: Initialize AffiliatePage
            log.debug("Initializing AffiliatePage object");

            AffiliatePage ap = new AffiliatePage(getDriver());

            // Step 8: Navigate to Affiliate Form
            log.debug("Navigating to Affiliate registration form");

            ap.navigateToAffiliateForm();

            log.info("Affiliate registration form opened successfully");

            // Step 9: Fill Affiliate Details
            log.debug("Entering affiliate registration details");

            ap.fillAffiliateDetails(
                "CloudBerry",
                "cloudberry.services",
                "123456",
                "Elakiya"
            );

            log.info("Affiliate details submitted successfully");

            // Step 10: Verify Affiliate Registration
            log.debug("Verifying affiliate registration status");

            try {

                boolean status = ap.isAffiliateAdded();

                log.debug(
                    "Affiliate registration status: {}",
                    status
                );

                Assert.assertTrue(
                    status,
                    "Affiliate details not added successfully."
                );

                log.info("Affiliate registration verification PASSED");

            } catch (AssertionError e) {

                log.error(
                    "Affiliate registration assertion FAILED",
                    e
                );

                // Rethrow to trigger RetryAnalyzer
                throw e;
            }

            log.info("Add Affiliate Test PASSED");

        } catch (AssertionError e) {

            // Assertion already logged above.
            // Rethrow to allow RetryAnalyzer to execute.
            throw e;

        } catch (Exception e) {

            log.error(
                "Add Affiliate Test FAILED due to unexpected exception",
                e
            );

            throw e;

        } finally {

            log.info(
                "========== Add Affiliate Test Finished =========="
            );
        }
    }
}