package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import pages.base.BasePage;
import pages.bingo.BingoPage;
import pages.home.HomepagePage;
import common.CommonActions;
import static common.Config.*;


public class BaseTest {
    protected static WebDriver driver;
    protected Actions builder;
    protected BasePage basePage;
    protected HomepagePage homepagePage;
    protected BingoPage bingoPage;

    @BeforeTest
    @Parameters({"browserName"})
    public void selectBrowser(@Optional("mozilla") String browserName) {
        PLATFORM_AND_BROWSER = browserName;
        driver = CommonActions.createDriver();
        homepagePage = new HomepagePage(driver);
        basePage = new BasePage(driver);
        builder = new Actions(driver);
        bingoPage = new BingoPage(driver);
    }


    public void pause(int time) {
        System.out.println("Pause - " + time / 1000 + " sek");
        builder.pause(time).perform();
    }


    public void printTestStart() {
        System.out.println("\n" + Thread.currentThread().getStackTrace()[2].getMethodName() + " - starts!\n" +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    public void printTestFinish() {
        System.out.println("\n" + Thread.currentThread().getStackTrace()[2].getMethodName() + " - Finished!\n" +
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }

    public boolean actualColorBetweenExpected(String actual, String expectedMin, String expectedMax) {
        int actualRed = Integer.parseInt(actual.substring(0,2),16);
        int actualGreen = Integer.parseInt(actual.substring(2,4),16);
        int actualBlue = Integer.parseInt(actual.substring(4,6),16);
        int expectedMinRed = Integer.parseInt(expectedMin.substring(0,2),16);
        int expectedMinGreen = Integer.parseInt(expectedMin.substring(2,4),16);
        int expectedMinBlue = Integer.parseInt(expectedMin.substring(4,6),16);
        int expectedMaxRed = Integer.parseInt(expectedMax.substring(0,2),16);
        int expectedMaxGreen = Integer.parseInt(expectedMax.substring(2,4),16);
        int expectedMaxBlue = Integer.parseInt(expectedMax.substring(4,6),16);
        if((actualRed >= expectedMinRed && actualRed <= expectedMaxRed) && (actualGreen >= expectedMinGreen &&
                actualGreen <= expectedMaxGreen ) && (actualBlue >= expectedMinBlue && actualBlue <= expectedMaxBlue)){
            return true;
        }else{
            return false;
        }
    }


    @AfterTest
    public void clearCookiesAndLocalStorage() {
        if (CLEAN_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
            System.out.println("AfterTest");
            if (HOLD_BROWSER_OPEN) {
                driver.quit();
            }
        }


    }



}
