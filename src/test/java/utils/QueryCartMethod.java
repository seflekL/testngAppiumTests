package utils;

import pages.QueryCartPage;

public class QueryCartMethod {
    QueryCartPage queryCartPage = new QueryCartPage();

    public static void autoLogin() {  // Metot zaten public
        QueryCartPage queryCartPage = new QueryCartPage();

        queryCartPage.signINUseEmailInsteadButtonElementi.click();
        ReusableMethods.wait(2);
        queryCartPage.signInEmailBoxElementi.click();
        queryCartPage.signInEmailBoxElementi.sendKeys(ConfigReader.getProperty("custUsername"));
        queryCartPage.signInPasswordBoxElementi.click();
        queryCartPage.signInPasswordBoxElementi.sendKeys(ConfigReader.getProperty("custPassword"));
        queryCartPage.signInContinueButtonElementi.click();
    }
}
