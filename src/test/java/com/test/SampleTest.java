package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SampleTest {

    @Test
    public void androidLaunchTest() throws MalformedURLException,InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        //options.setDeviceName("pixel_3a");
        options.setUdid("emulator-5554");
        //options.setUdid("268b2662");
        options.setApp(System.getProperty("user.dir")+"/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);

        //Thread.sleep(2000);
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver, Duration.ofSeconds(2));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("open menu")));


        androidDriver.findElement(AppiumBy.accessibilityId("open menu")).click();
        //Thread.sleep(1000);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]/android.widget.TextView")));

        androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]/android.widget.TextView")).click();
        //Thread.sleep(1000);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Username input field")));
        androidDriver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("Ravi");
        androidDriver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("Password");
        //Thread.sleep(1000);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]")));
        androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]")).click();
        Thread.sleep(1000);
        androidDriver.quit();

    }

    @Test
    public void iOSLaunchTest() throws MalformedURLException,InterruptedException {
        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName("iOS");
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setDeviceName("iPhone 15 Pro Max");
        //options.setUdid("30BF84B6-AC70-43EB-A0DD-8F37BB890A61");
        options.setPlatformVersion("17.0");
        options.setApp(System.getProperty("user.dir")+"/apps/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");

        IOSDriver iosDriver = new IOSDriver(new URL("http://127.0.0.1:4723"),options);
        Thread.sleep(2000);
        iosDriver.findElement(AppiumBy.accessibilityId("tab bar option menu")).click();
        //Thread.sleep(1000);

        iosDriver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
        //Thread.sleep(1000);
        iosDriver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("Ravi");
        iosDriver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("Password");
        //Thread.sleep(1000);
        iosDriver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"Login button\"]")).click();
        //Thread.sleep(1000);
        iosDriver.quit();
    }
}
