package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ChangeStoreNamePage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeStoreNamePageTest extends BaseTest {

    private LoginAndRegistrationPage loginAndRegPage;
    private MainPage mainPage;
    private ChangeStoreNamePage changeStoreNamePage;

    @BeforeEach
    public void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        changeStoreNamePage = new ChangeStoreNamePage(driver);
    }

    @Test
    @Description("Попытка ввода пустого значения вместо старого названия магазина")
    public void emptyOldStoreNameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("", "testerovochka");
        assertTrue(changeStoreNamePage.checkResultWithError(""),
                "Сообщение должно быть 'Название магазина не может быть пустым'");
    }

    @Test
    @Description("Попытка ввода пустого значения вместо нового названия магазина")
    public void emptyNewStoreNameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Магнат", "");
        assertTrue(changeStoreNamePage.checkResultWithError(""),
                "Сообщение должно быть 'Название магазина не может быть пустым'");
    }

    @Test
    @Description("Попытка смены названия несуществующего магазина")
    public void changeStoreNameNotExistsNameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Манго", "Магноли");
        assertTrue(changeStoreNamePage.checkResultWithError("Манго"),
                "Сообщение должно быть 'Магазин с названием 'Манго' не найден'");
    }

    @Test
    @Description("Попытка смены названия на занятое название магазина")
    public void changeStoreNameWithExistsNameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Магнат", "Пяточка");
        assertTrue(changeStoreNamePage.checkResultWithError("Пяточка"),
                "Сообщение должно быть 'Магазин с названием 'Пяточка' существует'");
    }

    @Test
    @Description("Попытка смены названия на текущее")
    public void changeStoreNameEqualsCurrentNameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Магнат", "Магнат");
        assertTrue(changeStoreNamePage.checkResultWithError(""),
                "Сообщение должно быть 'Название не может совпадать'");
    }

    @Test
    @Description("Попытка сменить название не в своем магазине")
    public void changeStoreNameAccessDeniedTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Пяточка", "Переселенец");
        assertTrue(changeStoreNamePage.checkResultWithError(""),
                "Сообщение должно быть 'Пользователю не принадлежит магазин'");
    }

    @Test
    @Description("Попытка поменять название на название с малым количеством символов")
    public void changeStoreNameSymbolLessLengthTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Магнат", "12");
        assertTrue(changeStoreNamePage.checkResultWithError(""),
                "Сообщение должно быть 'Название магазина должно быть от 3 до 30 символов'");
    }

    @Test
    @Description("Попытка поменять название на название с превышающим лимит символов")
    public void changeStoreNameSymbolMoreLengthTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Магнат", "MMaaagggnnnneeeetttttttttttoooooooooo");
        assertTrue(changeStoreNamePage.checkResultWithError(""),
                "Сообщение должно быть 'Название магазина должно быть от 3 до 30 символов'");
    }

    @Test
    @Description("Успешная смена названия магазина и возврат старого названия")
    public void changeStoreNameSuccess_Test() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeStoreName();

        changeStoreNamePage.storeNameSetValue("Магнат", "Магма");
        assertTrue(changeStoreNamePage.checkResult(
                "Тип операции: Смена названия",
                "Пользователь: test",
                "Новое название магазина: Магма"
        ), "Значения должны быть: 'Смена названия', 'test', 'Магма'");

        changeStoreNamePage.clickToHome();
        mainPage.clickToChangeStoreName();
        changeStoreNamePage.storeNameSetValue("Магма", "Магнат");

        assertTrue(changeStoreNamePage.checkResult(
                "Тип операции: Смена названия",
                "Пользователь: test",
                "Новое название магазина: Магнат"
        ), "Значения должны быть: 'Смена названия', 'test', 'Магнат'");
    }
}