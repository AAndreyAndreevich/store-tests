package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ProductOperationPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import pages.ResultProductOperationPage;
import utils.BaseTest;

public class ProductOperationsTests extends BaseTest {


    private static final Logger log = LoggerFactory.getLogger(ProductOperationsTests.class);
    private MainPage mainPage;
    private LoginAndRegistrationPage loginPage;
    private ProductOperationPage productOperation;
    private ResultProductOperationPage resultOperation;

    private final String URL = "http://localhost:8080/";

    @Test
    public void buyProductTest() {
        setSettingDriver();
        log.info("Вход");
        loginPage.loginIn("user", "user");
        mainPage.clickToBuy();
        log.info("Покупка");
        productOperation.enterStoreId("1");
        productOperation.enterProductId("1");
        productOperation.enterCount("1");
        productOperation.clickSubmit();
        log.info("Результат");
        Assertions.assertEquals(resultOperation.getSuccessText(), "Операция успешно завершена!");
        Assertions.assertEquals(resultOperation.getStoreName(), "Пяточка");
        Assertions.assertEquals(resultOperation.getProductName(), "Хлеб");
    }
    @Test
    public void sellProductTest() {
        setSettingDriver();
        log.info("Вход");
        loginPage.loginIn("user", "user");
        mainPage.clickToSell();
        log.info("Продажа");
        productOperation.enterStoreId("1");
        productOperation.enterProductId("1");
        productOperation.enterCount("1");
        productOperation.clickSubmit();
        log.info("Результат");
        Assertions.assertEquals(resultOperation.getSuccessText(), "Продажа успешно завершена!");
        Assertions.assertEquals(resultOperation.getStoreName(), "Пяточка");
        Assertions.assertEquals(resultOperation.getProductName(), "Хлеб");
    }

    private void setSettingDriver() {
        log.info("Подключение");
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginPage = new LoginAndRegistrationPage(driver);
        productOperation = new ProductOperationPage(driver);
        resultOperation = new ResultProductOperationPage(driver);
    }
}