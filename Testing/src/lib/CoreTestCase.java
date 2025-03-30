package lib;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    public AppiumDriver driver;
    public static String AppiumURL = "http://192.168.1.43:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Waysy\\IdeaProjects\\untitled\\apks\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    }
    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}