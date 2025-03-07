package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginAndRegistrationPage {

    private WebDriver driver;

    @FindBy(css = "input[name='username']")
    private WebElement usernameField;
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    @FindBy(css = "a[href='/registration']")
    private WebElement registrationButton;
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    @FindBy(id = "error-message")
    private WebElement errorText;
    @FindBy(css = "a[href='/login']")
    private WebElement loginButton;

    public LoginAndRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String text) {
        usernameField.sendKeys(text);
    }

    public void enterPassword(String text) {
        passwordField.sendKeys(text);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickRegistration() {
        registrationButton.click();
    }

    public void loginValue(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

    public void logInUser() {
        loginValue("user", "user");
    }

    public void logInNoStoreUser() {
        loginValue("nostore", "nostore");
    }

    public void logInNoBalanceUser() {
        loginValue("nobalance", "nobalance");
    }

    public boolean checkResultWithError() {
        return errorText.getText().contains("Пользователь с таким именем уже существует: ") ||
                errorText.getText().contains("Имя пользователя должно быть от ") ||
                errorText.getText().contains("Пароль должен быть от ");
    }
}