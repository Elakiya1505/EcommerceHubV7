package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC02_Login extends BaseClass {

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testLogin() {

        log.info("========== Login Test Started ==========");

        try {

            // Step 1: Initialize HomePage
            log.debug("Initializing HomePage object");

            HomePage hp = new HomePage(getDriver());

            log.info("HomePage initialized successfully");

            // Step 2: Navigate to Login Page
            log.debug("Clicking My Account menu");

            hp.clickMyAccount();

            log.info("My Account menu clicked successfully");

            log.debug("Navigating to Login page");

            hp.goToLogin();

            log.info("Navigated to Login page successfully");

            // Step 3: Initialize LoginPage
            log.debug("Initializing LoginPage object");

            LoginPage lp = new LoginPage(getDriver());

            // Step 4: Enter Login Credentials
            log.debug("Entering email address");

            lp.setEmail("krishnarajelakiya@gmail.com");

            log.info("Email entered successfully");

            log.debug("Entering password");

            lp.setPwd("test111");

            log.info("Password entered successfully");

            // Step 5: Click Login Button
            log.debug("Clicking Login button");

            lp.clickLogin();

            log.info("Login button clicked successfully");

            // Step 6: Initialize AccountPage
            log.debug("Initializing AccountPage object");

            AccountPage ap = new AccountPage(getDriver());

            // Step 7: Verify Login
            log.debug("Checking My Account confirmation");

            boolean status =
                    ap.getMyAccountConfirmation().isDisplayed();

            log.debug("My Account confirmation status: {}", status);

            // Assertion with try-catch
            try {

                Assert.assertTrue(
                    status,
                    "Login failed - My Account confirmation not displayed"
                );

                log.info("Login verification PASSED");

            } catch (AssertionError e) {

                log.error(
                    "Login verification FAILED - " +
                    "My Account confirmation not displayed",
                    e
                );

                // Rethrow to trigger RetryAnalyzer
                throw e;
            }

            log.info("Login Test PASSED");

        } catch (AssertionError e) {

            // Already logged in the assertion catch block.
            // Rethrow so TestNG can invoke RetryAnalyzer.
            throw e;

        } catch (Exception e) {

            log.error(
                "Login Test FAILED due to unexpected exception",
                e
            );

            // Rethrow so TestNG marks the test as failed.
            throw e;

        } finally {

            log.info(
                "========== Login Test Finished =========="
            );
        }
    }
}