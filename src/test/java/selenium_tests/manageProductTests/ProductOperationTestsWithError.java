package selenium_tests.manageProductTests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.GetAllProductsPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import pages.ManageProductPage;
import selenium_tests.utils.BaseTest;

public class ProductOperationTestsWithError extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private ManageProductPage managePage;
    private GetAllProductsPage allProductsPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка покупки с несуществующим ID продукта")
    public void buyProductWithNonexistentProductIdTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "6", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с несуществующим ID магазина")
    public void buyProductWithNonexistentStoreIdTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("69", "1", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с превышением вместимости склада")
    public void buyProductWithExceedingLimitTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "70");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с нулевым количеством")
    public void buyProductWithZeroQuantityTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "0");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки с нулевым балансом")
    public void buyProductWithZeroBalanceTest() {
        setSettingDriver();
        logInNoBalanceUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка покупки в чужой магазин")
    public void buyProductNotOwnStoreTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.buyClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи сверх лимит")
    public void sellProductExceedingLimitTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "71");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи с несуществующим ID продукта")
    public void sellProductWithNonexistentProductIdTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "6", "1");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи с несуществующим ID магазина")
    public void sellProductWithNonexistentStoreIdTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("69", "1", "1");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи с нулевым количеством")
    public void sellProductWithZeroQuantityTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("1", "1", "0");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка продажи из чужого магазина")
    public void sellProductNotOwnStoreTest() {
        setSettingDriver();
        logInUser();
        mainPage.clickToManage();
        manageSetValue("5", "1", "1");
        managePage.sellClick();
        Assertions.assertTrue(managePage.checkResultWithError());
    }

    @Test
    @Description("Попытка запросить список продуктов из пустого магазина")
    public void getProductListFromEmptyStoreTest() {
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