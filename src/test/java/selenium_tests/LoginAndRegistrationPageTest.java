package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.LoginAndRegistrationPage;
import selenium_tests.utils.BaseTest;

public class LoginAndRegistrationPageTest extends BaseTest {

    private LoginAndRegistrationPage logAndRegPage;

    private final String URL = "http://localhost:8080/";

    @Test
    @Description("Попытка регистрации с количеством символов для пароля выше допустимого")
    public void passwordSymbolMoreLimitTest() {

    }

    @Test
    @Description("Попытка регистрации с количеством символов для пароля ниже допустимого")
    public void passwordSymbolLessLimitTest() {

    }

    @Test
    @Description("Попытка регистрации с количеством символов для имени выше допустимого")
    public void usernameSymbolMoreLimitTest() {

    }

    @Test
    @Description("Попытка регистрации с количеством символов для имени ниже допустимого")
    public void usernameSymbolLessLimitTest() {

    }

    @Test
    @Description("Попытка зарегистрироваться с уже существующим именем пользователя")
    public void usernameIsExistsTest() {

    }

    @Test
    @Description("Успешная авторизация")
    public void successLoginTest() {

    }

    @Test
    @Description("Успешная регистрация")
    public void successRegistrationTest() {

    }

    private void setSettingDriver() {
        driver.get(URL);
        logAndRegPage = new LoginAndRegistrationPage(driver);
    }

}