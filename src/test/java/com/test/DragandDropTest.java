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
import java.util.Collections;

public class DragandDropTest {
    @Test
    public void dragAndDrop() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.findElement(AppiumBy.accessibilityId("Views")).click();
        androidDriver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement source = androidDriver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement target = androidDriver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

        Point sourceElementCenter = getCenterOfElement(source.getLocation(), source.getSize());
        Point targetElementCenter = getCenterOfElement(source.getLocation(), target.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(), sourceElementCenter))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(), targetElementCenter))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        androidDriver.perform(Collections.singletonList(sequence));

    }

    private Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth()/2,location.getY() + size.getHeight()/2);

    }
}
