package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Before;
import lib.ui.MainPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageObject extends MainPageObject{

    public static final String
    SEARCH_INIT_ELEMENT ="//*[contains(@text,'Поиск по Википедии')]",
    SEARCH_INPUT = "//*[contains(@text,'Поиск')]",
    SEARCH_RESULT = "//*[@class='android.view.ViewGroup']//*[@text='{SUBSTRING}']";



    public SearchPageObject(AppiumDriver driver) {
        super(driver);
//        this.driver = driver;
    }

//    public MainPageObject MainPageObject;
//    public void setUp() throws Exception{
//        super.setUp();
//        MainPageObject = new MainPageObject(driver);
//        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
//                "Невозможно нажать на поле ввода", 15);
//    }


    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно нажать на поле ввода", 15);

        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно нажать на поле ввода", 5);
    }


    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,
                "Невозможно нажать на поле ввода",15);
    }

    public static String getResultSearchElement(String substring){
        return SEARCH_RESULT.replace("{SUBSTRING}",substring);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Невозможно найти" + substring,15);
    }

    @Test
    public void testSearchElement() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("DNS");
        SearchPageObject.waitForSearchResult("российская торговая сеть по продаже бытовой техники и электроники");
    }




    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSecond);
        element.sendKeys(value);
        return element;
    }
}
