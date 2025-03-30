import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest extends CoreTestCase {
    // 2 usages

    public MainPageObject MainPageObject;
    public void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода", 15);
    }
}
