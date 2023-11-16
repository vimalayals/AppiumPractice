package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;

public class AddCartTest {
    @Test
    public void addCart() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        //options.setUdid("268b2662");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.findElement(AppiumBy.accessibilityId("open menu"));
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Sauce Labs Fleece Jacket\"]")).click();
        androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]/android.widget.TextView")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("open menu")).click();
        //androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item catalog\"]/android.widget.TextView"));
        androidDriver.findElement((By.xpath("//android.widget.TextView[@text=\"Catalog\"]"))).click();
        androidDriver.findElement((By.xpath("//android.widget.TextView[@text=\"Sauce Labs Bolt T-Shirt\"]"))).click();
        androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]/android.widget.TextView")).click();
        androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView")).click();
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Proceed To Checkout\"]")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        androidDriver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        androidDriver.findElement(AppiumBy.accessibilityId("Login button"));
        androidDriver.quit();
    }
}
