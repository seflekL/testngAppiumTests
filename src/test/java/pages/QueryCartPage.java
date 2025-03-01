package pages;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.time.Duration;

public class QueryCartPage {

    public QueryCartPage (){
      PageFactory.initElements(new AppiumFieldDecorator(Driver.getAndroidDriver(), Duration.ofSeconds(10)), this);
}
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")
    public WebElement notificationPopUpClick;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Home\")")
    public WebElement  homeButtonelementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Category\")" )
    public WebElement categoryButtonElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(18)")
    public WebElement cartButtonElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Wishlist\")")
    public WebElement whishListButtonElmenti;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Profile\")")
    public  WebElement profileButtonElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().className(\"android.view.View\").instance(7)")
    public WebElement mainscrenBannerElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Men\")")
    public  WebElement categoryMenElementButton;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Shopping Cart\")")
    public WebElement shoppingCartTextElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"*Use Email Instead\")")
    public WebElement signINUseEmailInsteadButtonElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    public WebElement signInEmailBoxElementi;

    @AndroidFindBy (uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    public WebElement signInPasswordBoxElementi;
    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Sign In\").instance(1)")
    public WebElement signInContinueButtonElementi;
    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Wishlist\")")
    public WebElement whishlistTextElementi;
    @AndroidFindBy (uiAutomator = "new UiSelector().description(\"Sing In To See Your Info\")")
    public WebElement profileSigntoSeeYourInfoTextElmenti;

    }

