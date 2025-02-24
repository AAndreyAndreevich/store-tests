package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import utils.BaseTest;

import java.util.List;

public class ProductOperationsTests extends BaseTest {


    private static final Logger log = LoggerFactory.getLogger(ProductOperationsTests.class);
    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private ManageProductPage managePage;
    private GetAllProductsPage getProductsPage;
    private CreateStorePage createStorePage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка получения списка продуктов из магазина в котором отсутствует склад")
    public void getAllProductsWithErrorTest() throws Exception {
        log.info("Получение списка продуктов тест");
        setSettingDriver();
        log.info("Авторизация на 'user'");
        loginAndRegPage.loginIn("user", "user");
        log.info("Переход на страницу с получением списка");
        mainPage.clickToProductsList();
        log.info("Выбор магазина с пустым складом");
        getProductsPage.storeSetValue("Магнат");
        getProductsPage.clickSubmit();
        log.info("Проверка соответствия результата");
        Assertions.assertEquals(getProductsPage.getAllProductsResult(),
                "Ошибка: Магазин пуст или его не существует");
    }

    @Test
    @Description("Покупка продукта")
    public void buyProductTest() {
        log.info("Покупка продукта тест");
        setSettingDriver();
        log.info("Авторизация на 'user'");
        loginAndRegPage.loginIn("user", "user");
        log.info("Переход на страницу с управлением продуктами");
        mainPage.clickToManage();
        log.info("Ввод данных");
        managePage.enterStoreId("1");
        managePage.enterProductId("1");
        managePage.enterCount("1");
        managePage.buyClick();
        log.info("Проверка соответствия результата");
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

    private void setSettingDriver() {
        log.info("Подключение");
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        managePage = new ManageProductPage(driver);
        getProductsPage = new GetAllProductsPage(driver);
        createStorePage = new CreateStorePage(driver);
    }
}