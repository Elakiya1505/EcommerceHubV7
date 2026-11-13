package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.NoSuchElementException;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import testBase.BaseClass;
import utilities.DataProviders;
import utilities.RetryAnalyzer;

public class TC02_Login2_DataDriven extends BaseClass {

    @Test(
        groups = {"sanity", "regression", "datadriven"},
        dataProvider = "LoginData",
        dataProviderClass = DataProviders.class,
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testLogin(String email, String pwd) {

        log.info("========== Data Driven Login Test Started ==========");
        log.info("Executing login test for email: {}", email);

        try {

            // Step 1: Reset application state
            log.debug("Navigating to application home page");

            getDriver().get(p.getProperty("appURL"));

            log.info("Application home page loaded");

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
            log.debug("Entering email address: {}", email);

            lp.setEmail(email);

            log.info("Email entered successfully");

            log.debug("Entering password");

            lp.setPwd(pwd);

            log.info("Password entered successfully");

            // Step 6: Click Login Button
            log.debug("Clicking Login button");

            lp.clickLogin();

            log.info("Login button clicked successfully");

            // Step 7: Initialize AccountPage
            log.debug("Initializing AccountPage object");

            AccountPage ap = new AccountPage(getDriver());

            // Step 8: Verify Login
            log.debug("Checking My Account confirmation message");

            boolean status = false;

            try {

                status = ap.getMyAccountConfirmation()
                           .isDisplayed();

                log.debug(
                    "My Account confirmation status: {}",
                    status
                );

            } catch (NoSuchElementException e) {

                log.warn(
                    "My Account confirmation element not found",
                    e
                );

                status = false;
            }

            // Step 9: Assertion and Logout
            try {

                if (status) {

                    log.info(
                        "Login successful for email: {}",
                        email
                    );

                    log.debug(
                        "Clicking My Account dropdown for logout"
                    );

                    ap.clickMyAccountDropDown();

                    log.debug("Clicking Logout");

                    ap.clickLogout();

                    log.info(
                        "Logout completed successfully for email: {}",
                        email
                    );

                    Assert.assertTrue(
                        status,
                        "Login verification failed for: " + email
                    );

                    log.info(
                        "Login verification PASSED for: {}",
                        email
                    );

                } else {

                    log.error(
                        "Login verification FAILED for email: {}",
                        email
                    );

                    Assert.fail(
                        "Login failed - My Account confirmation " +
                        "not displayed for: " + email
                    );
                }

            } catch (AssertionError e) {

                log.error(
                    "Assertion FAILED for email: {}",
                    email,
                    e
                );

                // Rethrow to trigger RetryAnalyzer
                throw e;
            }

            log.info(
                "Data Driven Login Test PASSED for email: {}",
                email
            );

        } catch (AssertionError e) {

            // Assertion failure already logged.
            // Rethrow so TestNG can invoke RetryAnalyzer.
            throw e;

        } catch (Exception e) {

            log.error(
                "Data Driven Login Test FAILED for email: {}",
                email,
                e
            );

            // Rethrow so TestNG marks the test as failed.
            throw e;

        } finally {

            log.info(
                "========== Data Driven Login Test Finished: {} ==========",
                email
            );
        }
    }
}