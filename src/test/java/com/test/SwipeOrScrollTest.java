package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class SwipeOrScrollTest {

    @Test
    public void swipeOrScroll() throws MalformedURLException{
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver. findElement(AppiumBy.accessibilityId("Views")).click();

        Dimension size = androidDriver.manage().window().getSize();
        int startX = size.getWidth()/2;
        int startY = size.getHeight()/2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);


        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
                Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction((finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        androidDriver.perform(Collections.singletonList(sequence));

    }
}
