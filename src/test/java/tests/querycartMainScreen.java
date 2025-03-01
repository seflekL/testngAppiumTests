package tests;

import io.appium.java_client.android.AndroidDriver;


import io.qameta.allure.Description;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.QueryCartPage;
import utils.ConfigReader;
import utils.Driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import utils.QueryCartMethod;
import utils.ReusableMethods;


public class querycartMainScreen {

    private static final Logger logger = LogManager.getLogger(querycartMainScreen.class);

    AndroidDriver driver = Driver.getAndroidDriver();
    QueryCartPage queryCartPage = new QueryCartPage();

    @Test
    @Description("Query Cart Main Screen")

    public void QueryCartMainScreenButtonTest() {
        logger.info("Uygulamanin Yuklendigi Dogrulanir");
        Assert.assertTrue(driver.isAppInstalled("com.wise.querycart"));

        logger.info("Ana ekran alt Kategorinde  kolay linkler (Home,Category,Whislist,Profile) goruntulenmeli");

        queryCartPage.notificationPopUpClick.click();
        ReusableMethods.wait(1);
        Assert.assertTrue(queryCartPage.homeButtonelementi.isDisplayed());
        Assert.assertTrue(queryCartPage.categoryButtonElementi.isDisplayed());
        Assert.assertTrue(queryCartPage.whishListButtonElmenti.isDisplayed());
        Assert.assertTrue(queryCartPage.profileButtonElementi.isDisplayed());


    }

    @Description("MainScreenBanner Test")
    @Test
    public void MainScreenBannerTest() {
        logger.info("Uygulama Baslatilir");
        Driver.getAndroidDriver();
        queryCartPage.notificationPopUpClick.click();
        logger.info("BannerSwipe Baslatilir");
        Actions action = new Actions(driver);
        ReusableMethods.waitFor(5);
        ReusableMethods.swipeBannerElementOnly(driver, queryCartPage.mainscrenBannerElementi, 3);

        driver.quit();
    }

    @Description("Floating Action  Buttonlarinin dogru sayflara yonledirme testi")
    @Test
    public void MainScreenCategoryButtonTest() {
        logger.info("CategoryButton Testi");
        Driver.getAndroidDriver();
        queryCartPage.notificationPopUpClick.click();
        queryCartPage.categoryButtonElementi.click();
        Assert.assertTrue(queryCartPage.categoryMenElementButton.isDisplayed());
    }

    @Description("Floating Action  Buttonlarinin dogru sayflara yonledirme testi")
    @Test
    public void MainScreenCartButtonTest() {
        logger.info("CartButton Testi");
        Driver.getAndroidDriver();
        ReusableMethods.waitForVisibility(queryCartPage.notificationPopUpClick,10);
        queryCartPage.notificationPopUpClick.click();
        ReusableMethods.wait(3);
        queryCartPage.cartButtonElementi.click();
         Assert.assertTrue(queryCartPage.shoppingCartTextElementi.isDisplayed());
    }
    @Description("Floating Action  Buttonlarinin dogru sayflara yonledirme testi")
    @Test
    public void MainScreenWishlistButtonTest() {
        logger.info("WishListButton Testi");
        Driver.getAndroidDriver();
        ReusableMethods.waitForVisibility(queryCartPage.notificationPopUpClick,10);
        queryCartPage.notificationPopUpClick.click();
        ReusableMethods.wait(2);
        queryCartPage.whishListButtonElmenti.click();
        QueryCartMethod.autoLogin();
        ReusableMethods.wait(2);
        queryCartPage.whishListButtonElmenti.click();
        Assert.assertTrue(queryCartPage.whishlistTextElementi.isDisplayed());
    }

    @Description("Floating Action  Buttonlarinin dogru sayflara yonledirme testi")
    @Test
    public void MainScreenProfileButtonTest() {
        logger.info("ProfileButton Testi");
        Driver.getAndroidDriver();
        ReusableMethods.waitForVisibility(queryCartPage.notificationPopUpClick,10);
        queryCartPage.notificationPopUpClick.click();
        queryCartPage.profileButtonElementi.click();
        Assert.assertTrue(queryCartPage.profileSigntoSeeYourInfoTextElmenti.isDisplayed());

    }


}
