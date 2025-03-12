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
                "Сообщение должно быть 'Пароль должен быть от 6 до 30 символов'");
    }

    @Test
    @Description("Попытка регистрации с количеством символов для пароля ниже допустимого")
    public void passwordSymbolLessLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("testTest", "123");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Сообщение должно быть 'Пароль должен быть от 6 до 30 символов'");
    }

    @Test
    @Description("Попытка регистрации с количеством символов для имени выше допустимого")
    public void usernameSymbolMoreLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("abcdefghijklmnopqrstu", "1234");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя должно быть от 4 до 20 символов'");
    }

    @Test
    @Description("Попытка регистрации с количеством символов для имени ниже допустимого")
    public void usernameSymbolLessLimitTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("testT", "1234");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Сообщение должно быть 'Имя пользователя должно быть от 4 до 20 символов'");
    }

    @Test
    @Description("Попытка зарегистрироваться с уже существующим именем пользователя")
    public void usernameIsExistsTest() {
        setSettingDriver();
        logAndRegPage.clickRegistration();
        logAndRegPage.loginValue("test", "testing");
        assertTrue(logAndRegPage.checkResultWithError(),
                "Сообщение должно быть 'Пользователь с таким именем уже существует: test'");
    }

    private void setSettingDriver() {
        driver.get(URL);
        logAndRegPage = new LoginAndRegistrationPage(driver);
    }

}