package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderAndFooterPage extends BasePage {

    private static final By confirmAgeBtn = By.cssSelector("a[data-age-check-confirm]");
    private static final By headerClientHubLink = By.xpath("//li[@ id='menu-item-23582']/a");
    private static final By headerHomeLink = By.xpath("//header//a[@ title='Home']");
    private static final By headerProductsDd = By.xpath("//header//a[@ title='Games']");
    private static final By headerCompanyDd = By.xpath("//li[@ id='menu-item-57201']/a");
    private static final By headerNewsLink = By.cssSelector("li[id='menu-item-9841']>a");
    private static final By headerContactLink = By.cssSelector("li[id='menu-item-1820']>a");
    private static final By headerBingoItem = By.cssSelector("li[id='menu-item-11081']>a");

    public HeaderAndFooterPage(WebDriver driver) {
        super(driver);
    }

    public void confirmAgeBtnClick() {
        try {
            waitElementIsClickable(driver.findElement(confirmAgeBtn)).click();
        } catch (Exception e) {
            System.out.println("Confirm-age popup wasn't opened");
        }
    }


    public String headerHomeLinkGetColor() {
        return getColor(headerHomeLink);
    }

    public String headerClientHubLinkGetColor() {
        return getColor(headerClientHubLink);
    }

    public String headerProductsDdGetColor() {
        return getColor(headerProductsDd);
    }

    public String headerCompanyDdGetColor() {
        return getColor(headerCompanyDd);
    }

    public String headerNewsLinkGetColor() {
        return getColor(headerNewsLink);
    }

    public String headerContactLinkGetColor() {
        return getColor(headerContactLink);
    }

    public String headerBingoItemGetColor() {
        return getColor(headerBingoItem);
    }


    public HeaderAndFooterPage headerHomeLinkMouseHover() {
        mouseHover(headerHomeLink);
        return this;
    }

    public HeaderAndFooterPage headerProductsDdMouseHover() {
        mouseHover(headerProductsDd);
        return this;
    }

    public HeaderAndFooterPage headerClientHubLinkMouseHover() {
        mouseHover(headerClientHubLink);
        return this;
    }

    public HeaderAndFooterPage headerCompanyDdMouseHover() {
        mouseHover(headerCompanyDd);
        return this;
    }

    public HeaderAndFooterPage headerNewsLinkMouseHover() {
        mouseHover(headerNewsLink);
        return this;
    }

    public HeaderAndFooterPage headerContactLinkMouseHover() {
        mouseHover(headerContactLink);
        return this;
    }

    public HeaderAndFooterPage headerBingoItemMouseHover() {
        mouseHover(headerBingoItem);
        return this;
    }

    public void headerBingoItemClick() {
        waitElementIsClickable(driver.findElement(headerBingoItem)).click();
    }
}
