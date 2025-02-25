package selenium_tests.manageProductTests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import selenium_tests.utils.BaseTest;

public class ProductOperationsTests extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private ManageProductPage managePage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Покупка продукта")
    public void buyProductTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        managePage.enterStoreId("1");
        managePage.enterProductId("1");
        managePage.enterCount("1");
        managePage.buyClick();
        Assertions.assertTrue(
                managePage.checkResult(
                        "Тип операции: Покупка продукта",
                        "Название продукта: Хлеб",
                        "Количество: 1",
                        "Магазин: Пяточка",
                        "Пользователь: user"
                )
        );
    }

    @Test
    @Description("Продажа продукта")
    public void sellProductTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        managePage.enterStoreId("1");
        managePage.enterProductId("1");
        managePage.enterCount("1");
        managePage.sellClick();
        Assertions.assertTrue(
                managePage.checkResult(
                        "Тип операции: Продажа продукта",
                        "Название продукта: Хлеб",
                        "Количество: 1",
                        "Магазин: Пяточка",
                        "Пользователь: user"
                )
        );
    }

    private void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        managePage = new ManageProductPage(driver);
    }

    private void logInUser() {
        loginAndRegPage.loginIn("user", "user");
    }
}