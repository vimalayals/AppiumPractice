package com.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {
    static AndroidDriver androidDriver ;
    public static AndroidDriver driverInitiate() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
        androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        return androidDriver;
    }
    public static void driverClose()  {
        androidDriver.quit();
    }
}
