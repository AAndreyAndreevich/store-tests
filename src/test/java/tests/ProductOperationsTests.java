package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ProductOperationPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import pages.ResultProductOperationPage;
import utils.BaseTest;

public class ProductOperationsTests extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginPage;
    private ProductOperationPage productOperation;
    private ResultProductOperationPage resultOperation;

    private final String URL = "http://localhost:8080/";

    @Test
    public void buyProductTest() throws Exception {
        setSettingDriver();

        loginPage.loginIn("user", "user");

        mainPage.clickToBuy();

        productOperation.enterStoreId("1");
        productOperation.enterProductId("1");
        productOperation.enterCount("1");
        productOperation.clickSubmit();

        Thread.sleep(5000);

        Assertions.assertEquals(resultOperation.getSuccessText(), "Покупка успешно завершена!");
        Assertions.assertEquals(resultOperation.getStoreName(), "Пяточка");
        Assertions.assertEquals(resultOperation.getProductName(), "Хлеб");
    }

    private void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginPage = new LoginAndRegistrationPage(driver);
        productOperation = new ProductOperationPage(driver);
        resultOperation = new ResultProductOperationPage(driver);
    }
}