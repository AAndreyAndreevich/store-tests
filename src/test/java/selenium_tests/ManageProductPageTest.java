package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import pages.ManageProductPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManageProductPageTest extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private ManageProductPage managePage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Покупка продукта")
    public void buyProductTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "1");
        managePage.buyClick();
        assertTrue(
                managePage.checkResult(
                        "Тип операции: Покупка продукта",
                        "Название продукта: Хлеб",
                        "Количество: 1",
                        "Магазин: Пяточка",
                        "Пользователь: user"
                ),
                "Проверяет соответствие результатов покупки," +
                        "Должны быть 'Покупка продукта', 'Хлеб', '1' , 'Пяточка', 'user'"
        );
    }

    @Test
    @Description("Продажа продукта")
    public void sellProductTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "1");
        managePage.sellClick();
        assertTrue(
                managePage.checkResult(
                        "Тип операции: Продажа продукта",
                        "Название продукта: Хлеб",
                        "Количество: 1",
                        "Магазин: Пяточка",
                        "Пользователь: user"
                ),
                "Проверяет соответствие результатов покупки," +
                        "Должны быть 'Продажа продукта', 'Хлеб', '1' , 'Пяточка', 'user'"
        );
    }

    @Test
    @Description("Попытка покупки с несуществующим ID продукта")
    public void buyProductWithNonexistentProductIdTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "6", "1");
        managePage.buyClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о покупке продукта с несуществующим ID");
    }

    @Test
    @Description("Попытка покупки с несуществующим ID магазина")
    public void buyProductWithNonexistentStoreIdTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("69", "1", "1");
        managePage.buyClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о покупке в магазин несуществующим ID");
    }

    @Test
    @Description("Попытка покупки с превышением вместимости склада")
    public void buyProductWithExceedingLimitTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "70");
        managePage.buyClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о превышении вместимости склада");
    }

    @Test
    @Description("Попытка покупки с нулевым количеством")
    public void buyProductWithZeroQuantityTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "0");
        managePage.buyClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о покупке с нулевым количеством");
    }

    @Test
    @Description("Попытка покупки с нулевым балансом")
    public void buyProductWithZeroBalanceTest() {
        setSettingDriver();
        loginAndRegPage.logInNoBalanceUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.buyClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о покупке с нулевым балансом");
    }

    @Test
    @Description("Попытка покупки в чужой магазин")
    public void buyProductNotOwnStoreTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.buyClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о покупке в чужой магазин");
    }

    @Test
    @Description("Попытка продажи сверх лимит")
    public void sellProductExceedingLimitTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "71");
        managePage.sellClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о продаже больше количества в наличии");
    }

    @Test
    @Description("Попытка продажи с несуществующим ID продукта")
    public void sellProductWithNonexistentProductIdTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "6", "1");
        managePage.sellClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о продаже с несуществующим ID продукта");
    }

    @Test
    @Description("Попытка продажи с несуществующим ID магазина")
    public void sellProductWithNonexistentStoreIdTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("69", "1", "1");
        managePage.sellClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о продаже из магазина с несуществующим ID");
    }

    @Test
    @Description("Попытка продажи с нулевым количеством")
    public void sellProductWithZeroQuantityTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "0");
        managePage.sellClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о продаже без наличия товара");
    }

    @Test
    @Description("Попытка продажи из чужого магазина")
    public void sellProductNotOwnStoreTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.sellClick();
        assertTrue(managePage.checkResultWithError(),
                "Должно быть сообщение-ошибка о продаже из чужого магазина");
    }

    private void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        managePage = new ManageProductPage(driver);
    }

    private void manageSetValue(String storeId, String productId, String count) {
        managePage.enterStoreId(storeId);
        managePage.enterProductId(productId);
        managePage.enterCount(count);
    }
}