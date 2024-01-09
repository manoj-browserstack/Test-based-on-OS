package com.browserstack;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class BStackDemoTest extends SeleniumTest {
    @Test
    public void addProductToCart() throws Exception {
        HashMap<String, Object> currentPlatform = BrowserStackSdk.getCurrentPlatform();
        Object osValue = currentPlatform.get("os");
        System.out.println(osValue);
        if ("Windows".equals(osValue)) {
            System.out.println("The current platform is Windows");
            // navigate to bstackdemo
            driver.get("https://www.bstackdemo.com");

            // Check the title
            Assert.assertTrue(driver.getTitle().matches("StackDemo"));

            // Save the text of the product for later verify
            String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();
            // Click on add to cart button
            driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

            // See if the cart is opened or not
            Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

            // Check the product inside the cart is same as of the main page
            String productOnCartText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")).getText();
            Assert.assertEquals(productOnScreenText, productOnCartText);
        } else {
            System.out.println("The current platform is not Windows");
            driver.get("https://www.google.com/");

            // Get the title of the page
            String title = driver.getTitle();

            // Validate the title
            Assert.assertEquals(title, "Google");
        }
    }
}
