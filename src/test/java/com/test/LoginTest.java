package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class LoginTest extends Base {

    @Test
    public void loginCheck() throws MalformedURLException {
        AndroidDriver driver = Base.driverInitiate();
        androidDriver.findElement(AppiumBy.accessibilityId("open menu")).click();
        androidDriver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]/android.widget.TextView")).click();


    }
}
