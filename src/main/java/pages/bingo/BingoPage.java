package pages.bingo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.HeaderAndFooterPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BingoPage extends HeaderAndFooterPage {
    public BingoPage(WebDriver driver) {
        super(driver);
    }

    private final By variantsTitle = By.xpath("//div[@ class='games']//*[@ class='row text-left']");
    private final By variantsSlider = By.xpath("//div[@ class='games']//div[@ class='slick-track']");
    private final By anyVariantItem = By.xpath("//div[@ class='games']//div[@ class='slick-track']/div");
    private final By rightArrowBtn = By.cssSelector("[class='right-arrow slick-arrow']");


    public BingoPage variantsTitleScrollTo() {
        scrollTo(variantsTitle);
        return this;
    }


    public BingoPage rightArrowBtnClick() {
        driver.findElement(rightArrowBtn).click();
        shortPause(300);
        return this;
    }


    public List<WebElement> getVisibleSliderItems() {
        List<WebElement> sliderItems = driver.findElements(anyVariantItem);
        sliderItems = sliderItems.stream()
                .filter(x -> x.getAttribute("aria-hidden").equals("false"))
                .collect(Collectors.toList());
        return sliderItems;

    }


    public List<List<String>> allShownVariants() {
        List<List<String>> variants = getVisibleVariants();
        int variantsQty = variants.size();
        String template = getVisibleVariantNames(variants);
        rightArrowBtnClick();
        while (!getVisibleVariantNames(getVisibleVariants()).equals(template)) {
            List<String> var = new ArrayList<>();
            var.add(getVisibleSliderItems().get(variantsQty - 1).findElement(By.tagName("p")).getText());
            var.add(getVisibleSliderItems().get(variantsQty - 1).findElement(By.tagName("img")).getAttribute("src"));
            variants.add(var);
            rightArrowBtnClick();
        }
        return variants;
    }


    public List<List<String>> uniqueVariants (List<List<String>> allShown){
        return allShown.stream().distinct().collect(Collectors.toList());
    }


    private String getVisibleVariantNames(List<List<String>> var) {
        String names = "";
        for (List<String> v : var) {
            names = names + v.get(0);
        }
        return names;
    }


    private List<List<String>> getVisibleVariants() {
        List<List<String>> variants = new ArrayList<>();
        for (WebElement el : getVisibleSliderItems()) {
            List<String> var = new ArrayList<>();
            var.add(el.findElement(By.tagName("p")).getText());
            var.add(el.findElement(By.tagName("img")).getAttribute("src"));
            variants.add(var);
        }
        return variants;
    }
}


