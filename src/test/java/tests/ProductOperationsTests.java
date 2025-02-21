package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ProductOperationPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import pages.ResultProductOperationPage;
import utils.BaseTest;

public class ProductOperationsTests extends BaseTest {

    private final MainPage mainPage = new MainPage(driver);
    private final LoginAndRegistrationPage loginPage = new LoginAndRegistrationPage(driver);
    private final ProductOperationPage productOperation = new ProductOperationPage(driver);
    private final ResultProductOperationPage resultOperation = new ResultProductOperationPage(driver);

    private final String URL = "http://localhost:8080/";

    @Test
    public void buyProductTest() throws Exception {
        driver.get(URL);

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

}