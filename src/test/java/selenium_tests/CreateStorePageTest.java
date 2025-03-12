package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.CreateStorePage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateStorePageTest extends BaseTest {

    private CreateStorePage createStorePage;
    private LoginAndRegistrationPage loginAndRegPage;
    private MainPage mainPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка создать магазин с пустым названием")
    public void createStoreWithEmptyNameTest() {
        setSettingDriver();
        loginAndRegPage.logInTestUser();
        String name = "";
        mainPage.clickToCreateStore();
        createStorePage.enterStoreName(name);
        assertTrue(createStorePage.checkResultWithError(name),
                "Сообщение должно быть 'Ошибка: Название магазина не может быть пустым'");
    }

    @Test
    @Description("Попытка создать магазин с занятым названием")
    public void createStoreWithBusyNameTest() {
        setSettingDriver();
        loginAndRegPage.logInTestUser();
        String name = "Пяточка";
        mainPage.clickToCreateStore();
        createStorePage.enterStoreName(name);
        assertTrue(createStorePage.checkResultWithError(name),
                "Сообщение должно быть 'Ошибка: Магазин с названием 'Пяточка' существует'");
    }

    private void setSettingDriver() {
        driver.get(URL);
        createStorePage = new CreateStorePage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        mainPage = new MainPage(driver);
    }
}
