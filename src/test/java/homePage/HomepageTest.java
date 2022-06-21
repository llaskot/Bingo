package homePage;

import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static constants.Constant.ExpectedValues.THEMES_QTY;
import static constants.Constant.Urls.*;
import static constants.Constant.Colors.*;

public class HomepageTest extends BaseTest {
    SoftAssert sAssert;

    @BeforeClass
    public void confirmAge(){
        if(!driver.getCurrentUrl().equals(HOMEPAGE_URL)){
            basePage.open(HOMEPAGE_URL);
        }
        homepagePage.confirmAgeBtnClick();
    }

    @BeforeMethod
    public void softAssert (){
        if(!driver.getCurrentUrl().equals(HOMEPAGE_URL) && !driver.getCurrentUrl().equals(HOMEPAGE_URL+"#")){
            basePage.open(HOMEPAGE_URL);
        }
        sAssert = new SoftAssert();
    }

    @AfterMethod
    public void goToPage(){
        if(!driver.getCurrentUrl().equals(HOMEPAGE_URL) && !driver.getCurrentUrl().equals(HOMEPAGE_URL+"#")) {
            System.out.println("After Method");
            basePage.open(HOMEPAGE_URL);
        }
    }


    @Test
    public void checkMousedOverItemsColorTest (){

        printTestStart();
        String actualColor;
/*
Check HOME
 */
        actualColor = homepagePage.headerHomeLinkGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header HOME link has incorrect base color\n");
        homepagePage.headerHomeLinkMouseHover();
        actualColor = homepagePage.headerHomeLinkGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header HOME link is moused over and has incorrect color\n");
/*
Check CLIENT HUB
 */

        actualColor = homepagePage.headerClientHubLinkGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header ClientHub link has incorrect base color\n");
        homepagePage.headerClientHubLinkMouseHover();
        actualColor = homepagePage.headerClientHubLinkGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header ClientHub link is moused over and has incorrect color\n");
/*
Check PRODUCTS
 */
        actualColor = homepagePage.headerProductsDdGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header Products dropdown has incorrect base color\n");
        homepagePage.headerProductsDdMouseHover();
        actualColor = homepagePage.headerProductsDdGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header Products dropdown is moused over and has incorrect color\n");

/*
Check Company
 */
        actualColor = homepagePage.headerCompanyDdGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header Company dropdown has incorrect base color\n");
        homepagePage.headerCompanyDdMouseHover();
        actualColor = homepagePage.headerCompanyDdGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header Company dropdown is moused over and has incorrect color\n");

   /*
Check News
 */
        actualColor = homepagePage.headerNewsLinkGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header News link has incorrect base color\n");
        homepagePage.headerNewsLinkMouseHover();
        actualColor = homepagePage.headerNewsLinkGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header News link is moused over and has incorrect color\n");

   /*
Check Contact
 */
        actualColor = homepagePage.headerContactLinkGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header Contact link has incorrect base color\n");
        homepagePage.headerContactLinkMouseHover();
        actualColor = homepagePage.headerContactLinkGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header Contact link is moused over and has incorrect color\n");

   /*
Check Bingo item
 */
        homepagePage.headerProductsDdMouseHover();
        actualColor = homepagePage.headerBingoItemGetColor();
        sAssert.assertEquals(actualColor, COLOR_BASE, "ERROR - Header Bingo item has incorrect base color\n");
        homepagePage.headerBingoItemMouseHover();
        actualColor = homepagePage.headerBingoItemGetColor();
        sAssert.assertTrue(actualColorBetweenExpected(actualColor, COLOR_MOUSE_HOVER_MIN, COLOR_MOUSE_HOVER_MAX),
                "ERROR - Header Bingo item is moused over and has incorrect color\n");


        System.out.println("\nChecking changing of items color:\n" +
                "Checked - Header HOME link \n" +
                "Checked - Header Products dropdown \n" +
                "Checked - Header Client Hub link \n" +
                "Checked - Header Company dropdown \n" +
                "Checked - Header News link \n"+
                "Checked - Header Contact link \n"+
                "Checked - Header Bingo Item \n");

        printTestFinish();
        sAssert.assertAll();
    }

    @Test(priority = 1)
    public void CheckingThemesTest() {
        printTestStart();

        int visibleSliderItemsQty;
        int allShownVariantsQty;
        int uniqueVariantsQty;
        int delta;
        homepagePage.headerProductsDdMouseHover()
                .headerBingoItemClick();
        bingoPage.variantsTitleScrollTo();

        visibleSliderItemsQty = bingoPage.getVisibleSliderItems().size(); //number of all themes can be visible at once

        List<List<String>> allShownVariants = bingoPage.allShownVariants();  //all themes can be visible

        allShownVariantsQty = allShownVariants.size(); //number of all themes can be visible

        List<List<String>> uniqueVariants = bingoPage.uniqueVariants(allShownVariants); //unique themes

        uniqueVariantsQty = uniqueVariants.size(); //quantity of unique themes

        delta = allShownVariantsQty - uniqueVariantsQty; /* difference between all shown and unique themes (must be
                                                                                           shown at once minus 1 )*/

/*
checking duplicated themes
 */
        sAssert.assertEquals(delta, visibleSliderItemsQty-1, "ERROR - If actual" +
                " > еxpected - there are duplicated themes. If actual < еxpected - incorrect slider work\n");

/*
checking expected themes number
 */
        sAssert.assertEquals(uniqueVariantsQty, THEMES_QTY, "ERROR - incorrect unique themes quantity\n");

/*
checking links contain theme name
 */
        for (List<String> theme : uniqueVariants) {
            String themeName = theme.get(0).replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            String themeLink = theme.get(1).replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            sAssert.assertTrue(themeLink.contains(themeName), "ERROR - Image link doesn't contain theme name: "+theme.get(0) + "\n");
        }

        System.out.println("Checked:\n" +
                "Unique Themes quantity \n" +
                "Duplicated Themes \n" +
                "Image link contains theme name");
        printTestFinish();
        sAssert.assertAll();
    }



}
