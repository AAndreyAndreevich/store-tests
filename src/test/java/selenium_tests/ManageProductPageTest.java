package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.GetAllProductsPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import pages.ManageProductPage;
import selenium_tests.utils.BaseTest;

public class ManageProductPageTest extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private ManageProductPage managePage;
    private GetAllProductsPage allProductsPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Покупка продукта")
    public void buyProductTest_Success() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "1");
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
    public void sellProductTest_Success() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "1");
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

    @Test
    @Description("Попытка покупки с несуществующим ID продукта")
    public void buyProductWithNonexistentProductIdTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "6", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с несуществующим ID магазина")
    public void buyProductWithNonexistentStoreIdTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("69", "1", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с превышением вместимости склада")
    public void buyProductWithExceedingLimitTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "70");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с нулевым количеством")
    public void buyProductWithZeroQuantityTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "0");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с нулевым балансом")
    public void buyProductWithZeroBalanceTest_Fail() {
        setSettingDriver();
        logInNoBalanceUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки в чужой магазин")
    public void buyProductNotOwnStoreTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи сверх лимит")
    public void sellProductExceedingLimitTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "71");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи с несуществующим ID продукта")
    public void sellProductWithNonexistentProductIdTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "6", "1");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи с несуществующим ID магазина")
    public void sellProductWithNonexistentStoreIdTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("69", "1", "1");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи с нулевым количеством")
    public void sellProductWithZeroQuantityTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "0");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи из чужого магазина")
    public void sellProductNotOwnStoreTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка запросить список продуктов из пустого магазина")
    public void getProductListFromEmptyStoreTest_Fail() {
        setSettingDriver();
        logInUser();
        mainPage.clickToProductsList();
        allProductsPage.storeSetValue("Бедняга");
        allProductsPage.clickSubmit();
        Assertions.assertEquals("Ошибка: Магазин пуст или его не существует",
                allProductsPage.getAllProductsResult());
    }

    private void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        managePage = new ManageProductPage(driver);
        allProductsPage = new GetAllProductsPage(driver);
    }

    private void manageSetValue(String storeId, String productId, String count) {
        managePage.enterStoreId(storeId);
        managePage.enterProductId(productId);
        managePage.enterCount(count);
    }

    private void logInUser() {
        loginAndRegPage.loginIn("user", "user");
    }

    private void logInNoStoreUser() {
        loginAndRegPage.loginIn("nostore", "nostore");
    }

    private void logInNoBalanceUser() {
        loginAndRegPage.loginIn("nobalance", "nobalance");
    }
}