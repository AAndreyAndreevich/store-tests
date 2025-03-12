package selenium_tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ChangePasswordPage;
import pages.LoginAndRegistrationPage;
import pages.MainPage;
import selenium_tests.utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangePasswordPageTest extends BaseTest {

    private LoginAndRegistrationPage loginAndRegPage;
    private MainPage mainPage;
    private ChangePasswordPage changePasswordPage;

    @BeforeEach
    public void setSettingDriver() {
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        changePasswordPage = new ChangePasswordPage(driver);
    }

    @Test
    @Description("Ввод пустого значения в поле с действующим паролем")
    public void changeEmptyCurrentPasswordInputTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("", "meow1234");
        assertTrue(changePasswordPage.checkResultWithError(),
                "Сообщение должно быть 'Пароль не может быть пустым'");
    }

    @Test
    @Description("Ввод пустого значения в поле с новым паролем")
    public void changeEmptyNewPasswordInputTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("testtest", "");
        assertTrue(changePasswordPage.checkResultWithError(),
                "Сообщение должно быть 'Пароль не может быть пустым'");
    }

    @Test
    @Description("Ввод неправильного действующего пароля")
    public void changePasswordNotEqualsCurrentPasswordTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("testotest", "testing");
        assertTrue(changePasswordPage.checkResultWithError(),
                "Сообщение должно быть 'Неправильно указан действующий пароль'");
    }

    @Test
    @Description("Ввод нового пароля похожего на текущий")
    public void changePasswordEqualsCurrentPasswordTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("testtest", "testtest");
        assertTrue(changePasswordPage.checkResultWithError(),
                "Сообщение должно быть 'Новый пароль должен отличаться от старого'");
    }

    @Test
    @Description("Ввод нового пароля меньше лимита символов")
    public void changePasswordSymbolLessLimitTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("testtest", "12345");
        assertTrue(changePasswordPage.checkResultWithError(),
                "Сообщение должно быть 'Пароль должен быть от 6 до 30 символов'");
    }

    @Test
    @Description("Ввод нового пароля превышающий лимит символов")
    public void changePasswordSymbolMoreLimitTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("testtest", "testpasswordtestpasswordtestpassword");
        assertTrue(changePasswordPage.checkResultWithError(),
                "Сообщение должно быть 'Пароль должен быть от 6 до 30 символов'");
    }

    @Test
    @Description("Успешная смена пароля, а потом возврат старого")
    public void changePasswordSuccessTest() {
        loginAndRegPage.logInTestUser();
        mainPage.clickToChangePassword();
        changePasswordPage.passwordSetValue("testtest", "testing");
        assertTrue(changePasswordPage.checkResult(
                "Тип операции: Смена пароля",
                "Пользователь: test"
        ));

        changePasswordPage.clickToHome();
        mainPage.clickToChangePassword();

        changePasswordPage.passwordSetValue("testing", "testtest");
        assertTrue(changePasswordPage.checkResult(
                "Тип операции: Смена пароля",
                "Пользователь: test"
        ));
    }
}