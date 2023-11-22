package hybridApp;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HybridAppTest {

    private static final String APP_ANDROID = "https://github.com/appium-pro/TheApp/releases/download/v1.12.0/TheApp.apk";
    private static final String APP_IOS = "https://github.com/appium-pro/TheApp/releases/download/v1.12.0/TheApp.app.zip";
    private static final String APPIUM = "http://localhost:4723";

    private IOSDriver driver;
//    public void launchAndroid() throws MalformedURLException {
//
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setPlatformName("Android");
//        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
//        options.setUdid("emulator-5554");
//        options.setApp(APP_ANDROID);
//
//        driver = new AndroidDriver(new URL(APPIUM), options);
//
//    }

    public void launchIOS() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setAutomationName(AutomationName.IOS_XCUI_TEST);
        options.setApp(System.getProperty("user.dir")+"/apps/TheApp-v1.9.0.app.zip");
        options.setDeviceName("iPhone 15 Pro Max");
        options.setPlatformVersion("17.0");
        driver = new IOSDriver(new URL(APPIUM), options);
    }

    @Before
    public void setup() throws Exception {
//        launchAndroid();
        launchIOS();

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Nullable
    private String getWebContext(IOSDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        System.out.println(" All contexts: " + contexts);
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                System.out.println("Web Context: " + context);
                return context;
            }
        }
        return null;
    }

    @Test
    public void testHybridApp() {

        driver.findElement(AppiumBy.accessibilityId("Webview Demo")).click();

        driver.context("NATIVE_APP");
        System.out.println(driver.getContextHandles());

        driver.get("https://github.com/cloudgrey-io");

    }
}
