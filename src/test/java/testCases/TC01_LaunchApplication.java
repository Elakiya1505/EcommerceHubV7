package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC01_LaunchApplication extends BaseClass {

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testLaunchApplication() {

        log.info("========== Launch Application Test Started ==========");

        try {

            // Step 1: Initialize HomePage
            log.debug("Initializing HomePage object");

            HomePage hp = new HomePage(getDriver());

            log.info("HomePage object initialized successfully");

            // Step 2: Get Application Title
            log.debug("Retrieving application page title");

            String actualTitle = getDriver().getTitle();

            log.info("Actual Application Title: {}", actualTitle);

            // Step 3: Define Expected Title
            String expectedTitle = "Your store of fun";

            log.debug("Expected Application Title: {}", expectedTitle);

            // Step 4: Validate Application Title
            log.info("Validating application page title");

            try {

                Assert.assertEquals(
                    actualTitle,
                    expectedTitle,
                    "Application title verification failed"
                );

                log.info("Application title verification PASSED");

            } catch (AssertionError e) {

                log.error(
                    "Application title verification FAILED"
                );

                log.error(
                    "Expected Title: {} | Actual Title: {}",
                    expectedTitle,
                    actualTitle
                );

                log.error("Assertion Failure Details", e);

                // Rethrow to trigger TestNG RetryAnalyzer
                throw e;
            }

            log.info("Launch Application Test PASSED");

        } catch (AssertionError e) {

            log.error(
                "Launch Application Test FAILED due to assertion",
                e
            );

            throw e;

        } catch (Exception e) {

            log.error(
                "Launch Application Test FAILED due to exception",
                e
            );

            throw e;

        } finally {

            log.info(
                "========== Launch Application Test Finished =========="
            );
        }
    }
}