package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.GetAllProductsPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllProductsPageTest extends BaseTest {

    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private GetAllProductsPage allProductsPage;

    @BeforeEach
    public void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        allProductsPage = new GetAllProductsPage(driver);
    }

    @Test
    @Description("Попытка запросить список продуктов из пустого магазина")
    public void getProductListFromEmptyStoreTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToProductsList();
        allProductsPage.storeSetValue("Бедняга");
        allProductsPage.clickSubmit();
        assertEquals("Ошибка: Магазин пуст или его не существует",
                allProductsPage.getAllProductsResult(),
                "Сообщение должно быть 'Ошибка: Магазин пуст или его не существует'");
    }

}