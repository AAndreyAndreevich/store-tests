package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CreateStorePage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

public class CreateStorePageTest extends BaseTest {

    private CreateStorePage createStorePage;
    private LoginAndRegistrationPage loginAndRegPage;
    private MainPage mainPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка создать магазин с пустым названием")
    public void createStoreWithEmptyNameTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        String name = "";
        mainPage.clickToCreateStore();
        createStorePage.enterStoreName(name);
        Assertions.assertTrue(createStorePage.checkResultWithError(name));
    }

    @Test
    @Description("Попытка создать магазин с занятым названием")
    public void createStoreWithBusyNameTest() {
        setSettingDriver();
        loginAndRegPage.logInUser();
        String name = "Пяточка";
        mainPage.clickToCreateStore();
        createStorePage.enterStoreName(name);
        Assertions.assertTrue(createStorePage.checkResultWithError(name));
    }

    private void setSettingDriver() {
        driver.get(URL);
        createStorePage = new CreateStorePage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        mainPage = new MainPage(driver);
    }
}
