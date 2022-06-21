package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

import static common.Config.*;
import static constants.Constant.TimeoutVariable.*;


public class CommonActions {





    public static WebDriver createDriver(){
        WebDriver driver = null;

        switch (PLATFORM_AND_BROWSER) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "mozilla":
                System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                Assert.fail("Incorrect platform or browser name: " + PLATFORM_AND_BROWSER);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        return driver;
    }
}
