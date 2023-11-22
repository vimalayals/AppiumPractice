package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;

public class FlowTest {
    @Test
    public void FlowTest() throws MalformedURLException, InterruptedException{
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.findElement(AppiumBy.accessibilityId("Text")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("Linkify")).click();
        androidDriver.findElement(By.id("io.appium.android.apis:id/text2")).click();
       // androidDriver.findElement(By.class("io.appium.android.apis:id/text2")).click();
    }
}
