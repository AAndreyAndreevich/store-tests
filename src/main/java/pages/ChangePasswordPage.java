package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends BasePage {

    @FindBy(id = "oldPassword")
    private WebElement oldPasswordField;
    @FindBy(id = "oldPassword")
    private WebElement newPasswordField;
    @FindBy(id = "changePasswordResult")
    private WebElement result;
    @FindBy(css = "button[onclick]")
    private WebElement submit;

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }

    public void passwordSetValue(String oldPass, String newPass) {
        oldPasswordField.sendKeys(oldPass);
        newPasswordField.sendKeys(newPass);
        submit.click();
    }

    public boolean checkResult(String operationName, String userName) {
        String xpathResult = "//div[@id='changePasswordResult']";
        return driver.findElement(By.xpath(xpathResult + "//p[1]")).getText().equals(operationName) &&
                driver.findElement(By.xpath(xpathResult + "//p[2]")).getText().equals(userName);
    }

    public boolean checkResultWithError() {
        String error = result.getText();
        return switch (error) {
            case "Ошибка: Пароль не может быть пустым",
                 "Ошибка: Пользователь не найден",
                 "Ошибка: Неправильно указан действующий пароль",
                 "Ошибка: Новый пароль должен отличаться от старого",
                 "Ошибка: Пароль должен быть от 6 до 30 символов" -> true;
            default -> false;
        };
    }
}
