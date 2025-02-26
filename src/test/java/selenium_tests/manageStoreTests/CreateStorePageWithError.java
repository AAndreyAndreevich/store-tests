package selenium_tests.manageStoreTests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import selenium_tests.utils.BaseTest;

public class CreateStorePageWithError extends BaseTest {

    private CreateStorePage createStorePage;
    private LoginAndRegistrationPage loginAndRegPage;
    private MainPage mainPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка создать магазин с пустым названием")
    public void createStoreWithEmptyNameTest() {
        setSettingDriver();
        logInUser();
        String name = "";
        mainPage.clickToCreateStore();
        createStorePage.enterStoreName(name);
        Assertions.assertTrue(createStorePage.checkResultWithError(name));
    }

    @Test
    @Description("Попытка создать магазин с занятым названием")
    public void createStoreWithBusyNameTest() {
        setSettingDriver();
        logInUser();
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