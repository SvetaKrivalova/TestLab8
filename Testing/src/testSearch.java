import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
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

public class testSearch {
    // 2 usages
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Home\\IdeaProjects\\Testing\\apks\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://192.168.1.43:4723/wd/hub"), capabilities);
//        driver = new AndroidDriver(new URL("http://Localhost:4723/wd/hub"), capabilities );
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testSearch() {
        driver.findElement(By.xpath("//*[@text='Пропустить']")).click();
        waitForElementAndClick(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода", 15);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "DNS", "Невозможно найти поле ввода",5);
        waitForElementAndClick(By.xpath("//*[@class='android.view.ViewGroup']//*[@text='российская торговая сеть по продаже бытовой техники и электроники']"),
                "Невозможно найти DNS", 15);
        WebElement description_element = waitForElementPresent(By.id("pcs-edit-section-title-description"),
                "Невозможно найти описание",15);
        String result = description_element.getText();



        Assert.assertEquals("Найдено несовпадение в описании статьи",
                "российская торговая сеть по продаже бытовой техники и электроники", result);
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }
}
