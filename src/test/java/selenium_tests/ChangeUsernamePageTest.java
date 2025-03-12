package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ChangeUsernamePage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeUsernamePageTest extends BaseTest {

    private LoginAndRegistrationPage loginAndRegPage;
    private MainPage mainPage;
    private ChangeUsernamePage changeUsernamePage;

    @BeforeEach
    public void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        changeUsernamePage = new ChangeUsernamePage(driver);
    }

    @Test
    @Description("Проверка ввода пустого значения вместо имени пользователя")
    public void emptyUsernameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("", "tester");
        assertTrue(changeUsernamePage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя не может быть пустым'");
    }

    @Test
    @Description("Проверка ввода пустого значения в поле нового имени")
    public void emptyNewUsernameTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("test", "");
        assertTrue(changeUsernamePage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя не может быть пустым'");
    }

    @Test
    @Description("Проверка на попытку смены чужого имени пользователя")
    public void changeUsernameWithAccessDeniedTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("nobalance", "haveamoney");
        assertTrue(changeUsernamePage.checkResultWithError(),
                "Сообщение должно быть 'Пользователю не принадлежит это имя'");
    }

    @Test
    @Description("Попытка смены имени на уже существующее")
    public void changeUsernameIsExistsTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("test", "nobalance");
        assertTrue(changeUsernamePage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя уже занято'");
    }

    @Test
    @Description("Попытка смены имени с малым количеством символов")
    public void changeUsernameWithSymbolLessLengthTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("test", "abc");
        assertTrue(changeUsernamePage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя должно быть от 4 до 20 символов'");
    }

    @Test
    @Description("Попытка смены имени с превышающим лимит символов")
    public void changeUsernameWithSymbolMoreLengthTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("test", "abcdefghijklmnopqrstu");
        assertTrue(changeUsernamePage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя должно быть от 4 до 20 символов'");
    }

    @Test
    @Description("Успешная смена имени и изменение имени на старое")
    public void changeUsernameSuccessTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("test", "tester");

        assertTrue(changeUsernamePage.checkResult(
                "Тип операции: Смена имени",
                "Пользователь: test -> tester"
        ), "Значения должно быть: 'Смена имени', 'test -> tester'");

        changeUsernamePage.clickToHome();
        mainPage.clickToChangeUsername();
        changeUsernamePage.usernameSetValue("tester", "test");

        assertTrue(changeUsernamePage.checkResult(
                "Тип операции: Смена имени",
                "Пользователь: tester -> test"
        ), "Значения должно быть: 'Смена имени', 'tester -> test'");
    }
}