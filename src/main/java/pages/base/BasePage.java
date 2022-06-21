package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.Constant.TimeoutVariable.EXPLICIT_WAIT;
import static constants.Constant.Urls.HOMEPAGE_URL;


public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public void open(String url) {
        driver.get(url);
    }

    public String getColor(By by) {
        String col = Color.fromString(driver.findElement(by).getCssValue("color")).asHex();
        return col.toLowerCase().trim().substring((col.length() - 6));
    }

    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }


    public WebElement waitElementIsClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void goHome() {
        open(HOMEPAGE_URL);
    }

    public void mouseHover(By by) {
        Actions builder = new Actions(driver);
        WebElement el = driver.findElement(by);
        builder.moveToElement(el).pause(300).build().perform();

    }

    public void scrollTo(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(by));
    }

    public void shortPause(int time) {
        Actions builder = new Actions(driver);
        builder.pause(time).perform();
    }

}
