package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.ProductPage;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC03_AddToCart extends BaseClass {

    @Test(
        groups = {"sanity", "regression"},
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    public void testAddToCart() {

        log.info("========== Add To Cart Test Started ==========");

        try {

            // Step 1: Navigate to application home page
            log.debug("Navigating to application home page");

            getDriver().get(p.getProperty("appURL"));

            log.info("Application home page loaded successfully");

            // Step 2: Initialize CategoryPage
            log.debug("Initializing CategoryPage object");

            CategoryPage cp = new CategoryPage(getDriver());

            log.info("CategoryPage initialized successfully");

            // Step 3: Click Laptops & Notebooks
            log.debug("Clicking Laptops & Notebooks category");

            cp.clickLaptopsAndNotebooks();

            log.info("Laptops & Notebooks category clicked");

            // Step 4: Click Show All
            log.debug("Clicking Show All Laptops & Notebooks");

            cp.clickShowAll();

            log.info("All laptops and notebooks displayed");

            // Step 5: Wait for product listing
            log.debug("Waiting 500 milliseconds for page loading");

            Thread.sleep(500);

            log.debug("Wait completed successfully");

            // Step 6: Select HP Product
            log.debug("Selecting HP product");

            cp.selectHPProduct();

            log.info("HP product selected successfully");

            // Step 7: Initialize ProductPage
            log.debug("Initializing ProductPage object");

            ProductPage pp = new ProductPage(getDriver());

            log.info("ProductPage initialized successfully");

            // Step 8: Set Delivery Date
            log.debug("Setting product delivery date");

            pp.setDeliveryDate();

            log.info("Delivery date set successfully");

            // Step 9: Click Add to Cart
            log.debug("Clicking Add to Cart button");

            pp.clickAddToCart();

            log.info("Add to Cart button clicked successfully");

            // Step 10: Verify success message
            log.debug("Verifying Add to Cart success message");

            try {

                boolean status = pp.isSuccessMessageDisplayed();

                log.debug(
                    "Add to Cart success message status: {}",
                    status
                );

                Assert.assertTrue(
                    status,
                    "Add to Cart Failed! Success message not displayed."
                );

                log.info(
                    "Add to Cart verification PASSED"
                );

            } catch (AssertionError e) {

                log.error(
                    "Add to Cart assertion FAILED",
                    e
                );

                // Rethrow so TestNG can invoke RetryAnalyzer
                throw e;
            }

            log.info("Add To Cart Test PASSED");

        } catch (AssertionError e) {

            // Assertion already logged above.
            // Rethrow to allow RetryAnalyzer to execute.
            throw e;

        } catch (InterruptedException e) {

            log.error(
                "Add To Cart Test interrupted during execution",
                e
            );

            // Restore interrupt status
            Thread.currentThread().interrupt();

            throw new RuntimeException(
                "Test execution interrupted", e
            );

        } catch (Exception e) {

            log.error(
                "Add To Cart Test FAILED due to unexpected exception",
                e
            );

            throw e;

        } finally {

            log.info(
                "========== Add To Cart Test Finished =========="
            );
        }
    }
}