package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC05_AddToWishList extends BaseClass {

    @Test(
        groups = { "regression" },
        retryAnalyzer = utilities.RetryAnalyzer.class
    )
    void testAddToWishList() {

        log.info("===== TC05_AddToWishList STARTED =====");

        try {
            // Step 1: Login
            log.debug("Initializing HomePage");
            HomePage hp = new HomePage(getDriver());

            log.info("Clicking My Account");
            hp.clickMyAccount();

            log.info("Navigating to Login page");
            hp.goToLogin();

            log.debug("Initializing LoginPage");
            LoginPage lp = new LoginPage(getDriver());

            log.info("Entering email");
            lp.setEmail("sid@cloudberry.services");

            log.info("Entering password");
            lp.setPwd("Test123");

            log.info("Submitting login");
            lp.clickLogin();

            // Step 2: Navigate to product
            log.debug("Initializing CategoryPage");
            CategoryPage cp = new CategoryPage(getDriver());

            log.info("Clicking Laptops & Notebooks category");
            cp.clickLaptopsAndNotebooks();

            log.info("Clicking Show All products");
            cp.clickShowAll();

            log.debug("Waiting briefly for products to load");
            Thread.sleep(500);

            log.info("Selecting HP product");
            cp.selectHPProduct();

            // Step 3: Add to wishlist
            log.debug("Initializing ProductPage");
            ProductPage pp = new ProductPage(getDriver());

            log.info("Clicking Add to Wishlist");
            pp.addToWishlist();

            boolean isWishlistSuccess = pp.isSuccessMessageDisplayed();
            log.info("Wishlist success message displayed: {}", isWishlistSuccess);

            // Assertion with logging + screenshot
            try {
                log.debug("Asserting wishlist success message");
                Assert.assertTrue(isWishlistSuccess, "Wishlist message not shown.");
                log.info("✅ Assertion PASSED: Product added to wishlist");

            } catch (AssertionError ae) {
                log.error("❌ Assertion FAILED: Wishlist success message not displayed", ae);

                String screenshotPath = captureScreen("TC05_AddToWishList");
                log.info("Screenshot captured at: {}", screenshotPath);

                throw ae; // required for RetryAnalyzer
            }

        } catch (InterruptedException ie) {
            log.error("Thread interrupted during wait in TC05_AddToWishList", ie);

            String screenshotPath = captureScreen("TC05_AddToWishList_INTERRUPTED");
            log.info("Screenshot captured at: {}", screenshotPath);

            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);

        } catch (Exception e) {
            log.error("Unexpected exception occurred during TC05_AddToWishList", e);

            String screenshotPath = captureScreen("TC05_AddToWishList_EXCEPTION");
            log.info("Screenshot captured at: {}", screenshotPath);

            throw e;

        } finally {
            log.info("===== TC05_AddToWishList FINISHED =====");
        }
    }
}