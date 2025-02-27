package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.GetAllProductsPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

public class GetAllProductsPageTest extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private GetAllProductsPage allProductsPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка запросить список продуктов из пустого магазина")
    public void getProductListFromEmptyStoreTest_Fail() {
        setSettingDriver();
        loginAndRegPage.logInUser();
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
        allProductsPage = new GetAllProductsPage(driver);
    }
}