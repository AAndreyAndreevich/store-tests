package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginAndRegistrationPage extends BasePage {

    @FindBy(css = "input[name='username']")
    private WebElement usernameField;
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    @FindBy(css = "a[href='/registration']")
    private WebElement registrationButton;

    public LoginAndRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String text) {
        usernameField.sendKeys(text);
    }

    public void enterPassword(String text) {
        usernameField.sendKeys(text);
    }

    public void clickRegistration() {
        registrationButton.click();
    }

    public void loginIn(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }
}