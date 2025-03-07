package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.LoginAndRegistrationPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAndRegistrationPageTest extends BaseTest {

    private LoginAndRegistrationPage logAndRegPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка регистрации с количеством символов для пароля выше допустимого")
    public void passwordSymbolMoreLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("testTest", "abcdefghijklmnopqrstuvwxyz12345");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Должно вернуться сообщение об ошибке, символов больше 30");
    }

    @Test
    @Description("Попытка регистрации с количеством символов для пароля ниже допустимого")
    public void passwordSymbolLessLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("testTest", "123");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Должно вернуться сообщение об ошибке, символов меньше 4");
    }

    @Test
    @Description("Попытка регистрации с количеством символов для имени выше допустимого")
    public void usernameSymbolMoreLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("abcdefghijklmnopqrstu", "1234");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Должно вернуться сообщение об ошибке, символов больше 20");
    }

    @Test
    @Description("Попытка регистрации с количеством символов для имени ниже допустимого")
    public void usernameSymbolLessLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("testT", "1234");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Должно вернуться сообщение об ошибке, символов меньше 6");
    }

    @Test
    @Description("Попытка зарегистрироваться с уже существующим именем пользователя")
    public void usernameIsExistsTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("testuser", "testing");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Должно вернуться сообщение об ошибке, пользователь с данным именем зарегистрирован");
    }

//      Пока что нет никаких сообщений свидетельствующих о том что пользователь авторизировался или зарегистрировался,
//      в текущий момент могу делать проверки на отображение элементов из меню которые доступны для авторизированных,
//      но не хочу
//    @Test
//    @Description("Успешная авторизация")
//    public void successLoginTest() {
//        setSettingDriver();
//        logAndRegPage.loginValue("testuser", "testing");
//    }
//
//    @Test
//    @Description("Успешная регистрация")
//    public void successRegistrationTest() {
//        setSettingDriver();
//        logAndRegPage.clickRegistration();
//        logAndRegPage.loginValue("testRegistration", "testing");
//    }

    private void setSettingDriver() {
        driver.get(URL);
        logAndRegPage = new LoginAndRegistrationPage(driver);
    }

}