package hybridApp;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NoSuchContextException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.chromium.ChromiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NativeToHybid {

    @Test
    public void nativeToHybridIOS() throws MalformedURLException, InterruptedException{

            XCUITestOptions options = new XCUITestOptions();
            options.setPlatformName("iOS");
            options.setAutomationName(AutomationName.IOS_XCUI_TEST);
            options.setApp(System.getProperty("user.dir")+"/apps/iOS-Simulator-NativeDemoApp-0.4.0.app.zip");
            options.setDeviceName("iPhone 15 Pro Max");
            options.setPlatformVersion("17.0");
            IOSDriver iosDriver = new IOSDriver(new URL("http://127.0.0.1:4723"),options);
            System.out.println(iosDriver.getContextHandles());
        Thread.sleep(10000);
            iosDriver.findElement(AppiumBy.accessibilityId("Webview")).click();
        System.out.println(iosDriver.getContextHandles());
            Thread.sleep(10000);
            iosDriver.quit();

    }
    @Test
    public void launchAndroid() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAppActivity(".MainActivity");
        options.setAppPackage("com.wdiodemoapp");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");
        //options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println(driver.getContextHandles());
        Thread.sleep(10000);
        driver.findElement(AppiumBy.accessibilityId("Webview")).click();
        System.out.println(driver.getContextHandles());
        Thread.sleep(10000);
        driver.quit();

    }

    @Nullable
    private String getWebContext(AndroidDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        System.out.println(" All contexts: " + contexts);
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP") && context.contains("sworkitapp")) {
                System.out.println("Web Context: " + context);
                return context;
            }
        }
        return null;
    }

    @Test
    public void launchSwornKill() throws MalformedURLException, InterruptedException, NoSuchContextException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAppActivity(".MainActivity");
        options.setAppPackage("sworkitapp.sworkit.com");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setUdid("emulator-5554");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        String sworkitWebContext = getWebContext(driver);
        driver.context(sworkitWebContext);
        //chromeDriver.findElement(By.xpath("//android.view.View[@content-desc=\"LET'S GET STARTED!\"]/android.widget.TextView")).click();
        System.out.println(driver.getContextHandles());
        Thread.sleep(2000);
        //driver.context("NATIVE_APP");
        //driver.findElement(By.xpath("//android.view.View[@content-desc=\"SIGN IN\"]/android.widget.TextView")).click();
        //Thread.sleep(1000);
        driver.quit();

    }
}
