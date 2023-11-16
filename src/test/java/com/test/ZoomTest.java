package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class ZoomTest {
    @Test

    public void zoom() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        options.setApp(System.getProperty(("user.dir")) + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.findElement(AppiumBy.accessibilityId("open menu")).click();
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"Drawing\"]")).click();
        WebElement element = androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"drawing screen\"]"));

        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() + 100,
                        centerOfElement.getY() - 100));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() - 100,
                        centerOfElement.getY() + 100));
        androidDriver.perform((Arrays.asList(sequence,sequence2)));
    }

        private Point getCenterOfElement(Point location, Dimension size) {
            return new Point(location.getX() + size.getWidth()/2,location.getY() + size.getHeight()/2);

        }
    }