package utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
 import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.MobileBy.AndroidUIAutomator;
import static org.openqa.selenium.By.xpath;

public class ReusableMethods {


    public static void koordinatTiklamaMethodu(int x,int y,int bekleme) throws InterruptedException {
        TouchAction action=new TouchAction<>(Driver.getAndroidDriver());
        action.press(PointOption.point(x,y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(bekleme)))
                .release()
                .perform();
    }

    public static void scrollWithUiScrollableAndClick(String elementText) {
        AndroidDriver driver = (AndroidDriver)  Driver.getAndroidDriver();
       driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))"));
       driver.findElement(xpath("//*[@text='" + elementText + "']")).click();

    }

    public static void scrollWithUiScrollable(String elementText){
        AndroidDriver driver = (AndroidDriver)  Driver.getAndroidDriver();
        driver.findElement(androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + elementText + "\"))"));

    }



    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot)Driver.getAndroidDriver();

        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static void ekranKaydirmaMethodu(int pressx,int pressy,int wait,int moveX,int moveY){
        TouchAction action=new TouchAction<>(Driver.getAndroidDriver());
        action.press(PointOption.point(pressx,pressy))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(wait)))
                .moveTo(PointOption.point(moveX,moveY))
                .release().perform();
    }

    public static void dikeyKaydirma(RemoteWebDriver driver, double baslangicYuzdesi, double bitisYuzdesi, double sabitYuzde, int sure) {
        // 1. WebDriver'ın pencere boyutunu alır
        Dimension boyut = driver.manage().window().getSize();
        // 2. Sabit noktanın x koordinatını hesaplar (pencere genişliğinin belirtilen yüzdesi)
        int sabitNokta = (int) (boyut.width * sabitYuzde);
        // 3. Başlangıç noktasının y koordinatını hesaplar (pencere yüksekliğinin başlangıç yüzdesi)
        int baslangicNoktasi = (int) (boyut.height * baslangicYuzdesi);
        // 4. Bitiş noktasının y koordinatını hesaplar (pencere yüksekliğinin bitiş yüzdesi)
        int bitisNoktasi = (int) (boyut.height * bitisYuzdesi);
        // 5. Yeni bir PointerInput (parmak girişi) oluşturur, türü TOUCH (DOKUNMA) ve adı "finger"
        PointerInput parmak = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // 6. Kaydırma işlemi için bir Sequence (dizi) oluşturur, 1 adımlı
        Sequence kaydirma = new Sequence(parmak, 1)
                // Başlangıç noktasına parmağı hareket ettirme eylemi ekler
                .addAction(parmak.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sabitNokta, baslangicNoktasi))
                // Parmak basma (dokunma) eylemi ekler
                .addAction(parmak.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                // Belirtilen süre boyunca parmağı belirtilen bitiş noktasına hareket ettirme eylemi ekler
                .addAction(parmak.createPointerMove(Duration.ofMillis(sure), PointerInput.Origin.viewport(), sabitNokta, bitisNoktasi))
                // Parmak kaldırma (dokunmayı sonlandırma) eylemi ekler
                .addAction(parmak.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // 7. Oluşturulan kaydırma işlemini WebDriver üzerinde gerçekleştirir
        driver.perform(Collections.singletonList(kaydirma));
    }

    public static void bekle(int saniye){

        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void screenShotElement(String text) throws IOException {
        WebElement element = Driver.getAndroidDriver().findElement(xpath("//*[@text='"+text+"']"));
        Point location = element.getLocation();
        Dimension size = element.getSize();

        // Ekran görüntüsünü alın ve belirli bölgeyi kırpın
        File screenshot = Driver.getAndroidDriver().getScreenshotAs(OutputType.FILE);
        BufferedImage fullImage = ImageIO.read(screenshot);
        BufferedImage croppedImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(), size.getHeight());

        // Kırpılmış görüntüyü kaydedin
        File output = new File("kırpılmış_screenshot.png");
        ImageIO.write(croppedImage, "png", output);

        // Bağlantıyı kapat
        Driver.quitAppiumDriver();
    }
    ///update ver

    private static int timeout = 5;



    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getAndroidDriver().getWindowHandle();
        for (String handle : Driver.getAndroidDriver().getWindowHandles()) {
            Driver.getAndroidDriver().switchTo().window(handle);
            if (Driver.getAndroidDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getAndroidDriver().switchTo().window(origin);
    }

    //========Url Karşılaştırma=====//
    public static void erisimTesti(String expectedUrl) {
        String actualUrl = Driver.getAndroidDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }


    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getAndroidDriver());
        actions.moveToElement(element).perform();
    }


    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }


    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getAndroidDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }


    //===============Thread.sleep Wait==============//
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(10));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }


    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getAndroidDriver())
                .withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(15))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }


    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getAndroidDriver()).doubleClick(element).build().perform();
    }


    /**
     * @param element
     * @param check
     */
    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }


    /**
     * Selects a random value from a dropdown list and returns the selected Web Element
     *
     * @param select
     * @return
     */
    public static WebElement selectRandomTextFromDropdown(Select select) {
        Random random = new Random();
        List<WebElement> weblist = select.getOptions();
        int optionIndex = 1 + random.nextInt(weblist.size() - 1);
        select.selectByIndex(optionIndex);
        return select.getFirstSelectedOption();
    }

    public static void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }


    public static void waitAndClick(WebElement element) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }


    public static void waitAndSendText(WebElement element, String text, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }


    public static void waitAndSendText(WebElement element, String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }


    public static void waitAndSendTextWithDefaultTime(WebElement element, String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static String waitAndGetText(WebElement element, int timeout) {
        String text = "";
        for (int i = 0; i < timeout; i++) {
            try {
                text = element.getText();
                return text;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
        return null;
    }


    public static void wait2(int sec) {
        try {
            Thread.sleep(1000 * sec);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //5 seconds
    public static void waitAndClickElement(WebElement element, int seconds) {
        for (int i = 0; i < seconds; i++) {

            try {
                element.click();
                break;
            } catch (Exception e) {
                wait2(1);
            }
        }
    }

    public static void wait(int secs) {

        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static Boolean waitForInVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getAndroidDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getAndroidDriver();
        jse.executeScript(command, element);
    }


    public static void selectAnItemFromDropdown(WebElement item, String selectableItem) {
        wait(5);
        Select select = new Select(item);
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equalsIgnoreCase(selectableItem)) {
                select.getOptions().get(i).click();
                break;
            }
        }
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getAndroidDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getAndroidDriver()).executeScript("arguments[0].click();", element);
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param elements
     */
    public static void clickWithJSAsList(List<WebElement> elements) {
        for (WebElement each : elements) {
            ((JavascriptExecutor) Driver.getAndroidDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForVisibility(each, 5));
            ((JavascriptExecutor) Driver.getAndroidDriver()).executeScript("arguments[0].click();", each);
        }
    }


    public static void selectByVisibleText(WebElement element, String text) {
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(text);
    }


    public static void selectByIndex(WebElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }


    public static void selectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        List<WebElement> elementCount = objSelect.getOptions();
        objSelect.selectByValue(value);
        System.out.println("number of elements: " + elementCount.size());
    }


    public static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void waitAndClickLocationText(WebElement element, String value) {
        Driver.getAndroidDriver().findElement(By.xpath("//*[text()='" + value + "']")).click();
    }



    public static List<String> getStringList(List<WebElement> kaynakList) {

        List<String> stringList = new ArrayList<>();

        for (WebElement eachElement : kaynakList
        ) {

            stringList.add(eachElement.getText());

        }


        return stringList;
    }

    public static void switchWindowByUrl(WebDriver driver, String hedefUrl) {

        Set<String> acikWindowlarinWhdSeti = driver.getWindowHandles();
        for (String eachWhd : acikWindowlarinWhdSeti
        ) {
            // once bizim oglanin getirdigi whd ile bir window'a gecis yapalim
            driver.switchTo().window(eachWhd);
            String gecilenSayfaUrl = driver.getCurrentUrl();

            if (gecilenSayfaUrl.equals(hedefUrl)) {
                break;
            }
        }
    }

    public static void switchWindowByTitle(WebDriver driver, String hedefTitle) {
        Set<String> acikWindowlarinWhdSeti = driver.getWindowHandles();
        for (String eachWhd : acikWindowlarinWhdSeti
        ) {
            // once bizim oglanin getirdigi whd ile bir window'a gecis yapalim
            driver.switchTo().window(eachWhd);
            String gecilenSayfaTitle = driver.getTitle();

            if (gecilenSayfaTitle.equals(hedefTitle)) {
                break;
            }
        }
    }

    public static void getFullScreenshot(WebDriver driver, String screenshotIsmi) {
        // 1.adim screenshot objesi olusturmak ve deger olarak driver'imizi atamak
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim screenshot'i kaydedecegimiz File'i olusturun
        File tumSayfaSS = new File("target/ekranGoruntuleri/" + screenshotIsmi + ".png");

        // 3.adim screenshot'i alip gecici bir dosyaya kopyalayalim
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi, asil kaydetmek istedigimiz dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya, tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getFullScreenshot(WebDriver driver) {
        // dosya isimlerine tarih etiketi ekleyelim
        // ... 240829114023 gibi bir etiket eklemek dosya ismini benzersiz yapar

        LocalDateTime zaman = LocalDateTime.now(); // 2024.08.29T11:42:23:123456
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarihEtiketi = zaman.format(dateTimeFormatter); // 240829114023

        // 1.adim screenshot objesi olusturmak ve deger olarak driver'imizi atamak
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim screenshot'i kaydedecegimiz File'i olusturun
        File tumSayfaSS = new File("target/ekranGoruntuleri/TumSayfaSS_" + tarihEtiketi + ".png");

        // 3.adim screenshot'i alip gecici bir dosyaya kopyalayalim
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi, asil kaydetmek istedigimiz dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya, tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getWebelementScreenshot(WebElement istenenWebelement) {

        // tarih etiketi olusturalim
        LocalDateTime zaman = LocalDateTime.now(); // 2024.08.29T11:42:23:123456
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarihEtiketi = zaman.format(dateTimeFormatter); // 240829114023


        // 1.adim kullanacagimiz WebElementi locate edip kaydedin

        // 2.adim kaydedeceginiz dosyayi olusturun
        File webelementSS = new File("target/ekranGoruntuleri/webelementSS_" + tarihEtiketi + ".png");

        // 3.adim webelementi kullanarak screenshot'i alip gecici dosyaya kaydedin
        File geciciDosya = istenenWebelement.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi asil dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya, webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getWebelementScreenshot(WebElement istenenWebelement, String resimIsmi) {

        // tarih etiketi olusturalim
        LocalDateTime zaman = LocalDateTime.now(); // 2024.08.29T11:42:23:123456
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarihEtiketi = zaman.format(dateTimeFormatter); // 240829114023


        // 1.adim kullanacagimiz WebElementi locate edip kaydedin

        // 2.adim kaydedeceginiz dosyayi olusturun
        File webelementSS = new File("target/ekranGoruntuleri/" + resimIsmi + "_" + tarihEtiketi + ".png");

        // 3.adim webelementi kullanarak screenshot'i alip gecici dosyaya kaydedin
        File geciciDosya = istenenWebelement.getScreenshotAs(OutputType.FILE);

        // 4.adim gecici dosyayi asil dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciDosya, webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scrollWithUiScrollableAndClickLeo(String elementText) {
        AndroidDriver driver = (AndroidDriver) Driver.getAndroidDriver();

        try {
            // Elementi bulana kadar kaydırma yap
            driver.findElement(AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"" + elementText + "\"));"
            )).click();
        } catch (Exception e) {
            throw new RuntimeException("Belirtilen element bulunamadı veya kaydırma yapılamadı: " + elementText);
        }
    }

    public static void phonekeyboardenterTextLeo(String text) {
        AndroidDriver driver = Driver.getAndroidDriver();

        for (char ch : text.toCharArray()) {
            switch (ch) {
                case 'a': driver.pressKey(new KeyEvent(AndroidKey.A)); break;
                case 'b': driver.pressKey(new KeyEvent(AndroidKey.B)); break;
                case 'c': driver.pressKey(new KeyEvent(AndroidKey.C)); break;
                case 'd': driver.pressKey(new KeyEvent(AndroidKey.D)); break;
                case 'e': driver.pressKey(new KeyEvent(AndroidKey.E)); break;
                case 'f': driver.pressKey(new KeyEvent(AndroidKey.F)); break;
                case 'g': driver.pressKey(new KeyEvent(AndroidKey.G)); break;
                case 'h': driver.pressKey(new KeyEvent(AndroidKey.H)); break;
                case 'i': driver.pressKey(new KeyEvent(AndroidKey.I)); break;
                case 'j': driver.pressKey(new KeyEvent(AndroidKey.J)); break;
                case 'k': driver.pressKey(new KeyEvent(AndroidKey.K)); break;
                case 'l': driver.pressKey(new KeyEvent(AndroidKey.L)); break;
                case 'm': driver.pressKey(new KeyEvent(AndroidKey.M)); break;
                case 'n': driver.pressKey(new KeyEvent(AndroidKey.N)); break;
                case 'o': driver.pressKey(new KeyEvent(AndroidKey.O)); break;
                case 'p': driver.pressKey(new KeyEvent(AndroidKey.P)); break;
                case 'q': driver.pressKey(new KeyEvent(AndroidKey.Q)); break;
                case 'r': driver.pressKey(new KeyEvent(AndroidKey.R)); break;
                case 's': driver.pressKey(new KeyEvent(AndroidKey.S)); break;
                case 't': driver.pressKey(new KeyEvent(AndroidKey.T)); break;
                case 'u': driver.pressKey(new KeyEvent(AndroidKey.U)); break;
                case 'v': driver.pressKey(new KeyEvent(AndroidKey.V)); break;
                case 'w': driver.pressKey(new KeyEvent(AndroidKey.W)); break;
                case 'x': driver.pressKey(new KeyEvent(AndroidKey.X)); break;
                case 'y': driver.pressKey(new KeyEvent(AndroidKey.Y)); break;
                case 'z': driver.pressKey(new KeyEvent(AndroidKey.Z)); break;
                default:
                    System.out.println("Unsupported character: " + ch);
            }
        }
    }

    public static void swipeByCoordinatesLeo(AndroidDriver driver, int startX, int startY, int endX, int endY, int duration) {
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY)) // Başlangıç noktası
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))) // Kaydırma süresi
                .moveTo(PointOption.point(endX, endY)) // Bitiş noktası
                .release()
                .perform();
    }
    public static void kaydirmaYapLeo(int baslangicX, int baslangicY, int bitisX, int bitisY, int sure, int tekrarSayisi) {
        // Dokunma işlemi için bir PointerInput oluşturuyoruz
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 0; i < tekrarSayisi; i++) {
            Sequence swipe = new Sequence(finger, 1);

            // Başlangıç noktasına dokunma
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), baslangicX, baslangicY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            // Kaydırma hareketini süre boyunca gerçekleştir
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(sure), PointerInput.Origin.viewport(), bitisX, bitisY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Eylemi driver ile gerçekleştiriyoruz
            ((AppiumDriver) Driver.getAndroidDriver()).perform(Collections.singletonList(swipe));

            // Her kaydırma arasında kısa bir bekleme (isteğe bağlı)
            try {
                Thread.sleep(500); // milisaniye cinsinden bekleme süresi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void kaydirmasonarasiKordinatsecLeo(int x, int y) {
        // Dokunma işlemi için bir PointerInput oluşturuyoruz
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        // Belirtilen koordinata dokunma
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Eylemi driver ile gerçekleştiriyoruz
        ((AppiumDriver) Driver.getAndroidDriver()).perform(Collections.singletonList(tap));
    }
    public static void swipeBannerRightToLeft(AndroidDriver driver, WebElement element) {
        int startX = element.getLocation().getX() + element.getSize().getWidth() - 10; // Sağ kenardan başla
        int endX = element.getLocation().getX() + 10; // Sol kenara git
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2); // Yatayda ortala

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public static void swipeBannerElementOnly(AndroidDriver driver, WebElement element, int swipeCount) {
        for (int i = 0; i < swipeCount; i++) { // Belirtilen sayıda swipe yap
            int elementWidth = element.getSize().getWidth();
            int elementHeight = element.getSize().getHeight();
            int startX = element.getLocation().getX() + (int) (elementWidth * 0.8); // Sağdan %80 içeride başla
            int endX = element.getLocation().getX() + (int) (elementWidth * 0.2); // Sola %20 içeride git
            int centerY = element.getLocation().getY() + (elementHeight / 2); // Elementin ortasında kal

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence swipe = new Sequence(finger, 0);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));

            // Her swipe işleminden sonra biraz bekleyelim ki kaydırma düzgün yapılsın
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


