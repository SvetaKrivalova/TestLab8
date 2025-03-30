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

public class addBookList {
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
                "The Hobbit, or There and Back Again", "Невозможно найти поле ввода",10);
        waitForElementAndClick(By.xpath("//*[@text='Хоббит, или Туда и обратно' and @id='page_list_item_title']"),
                "Невозможно нажать на Хоббит, или Туда и обратно", 10);
        waitForElementAndClick(By.xpath("//*[@text='Сохранить' and @id='page_save']"),
                "Невозможно нажать на Сохранить",10);
        waitForElementAndClick(By.xpath("//*[@text='Добавить в список']"),
                "Не удалось найти поле поиска",10);
        waitForElementAndSendKeys(By.xpath("//*[@id='text_input']"),
                "The Hobbit", "Не удалось найти поле для ввода текста", 15);
        waitForElementAndClick(By.xpath("//*[@text='ОК']"),
                "Невозможно нажать на ОК", 15);
        waitForElementAndClick(By.xpath("//*[@text='Посмотреть список']"),
                "Невозможно нажать на Посмотреть список", 15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_overflow_menu']"),
                "Невозможно нажать на меню", 15);
        waitForElementAndClick(By.xpath("//*[@text='Удалить список']"),
                "Невозможно нажать на Удалить список", 15);
        waitForElementAndClick(By.xpath("//*[@text='ОК']"),
                "Невозможно нажать на ОК", 15);
    }

    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.clear();
        return element;
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
