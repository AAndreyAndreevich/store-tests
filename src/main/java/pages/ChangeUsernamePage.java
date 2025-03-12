package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangeUsernamePage extends BasePage {

    @FindBy(id = "oldName")
    private WebElement oldNameField;
    @FindBy(id = "newName")
    private WebElement newNameField;
    @FindBy(id = "changeUsernameResult")
    private WebElement result;
    @FindBy(css = "button[onclick]")
    private WebElement submit;

    public ChangeUsernamePage(WebDriver driver) {
        super(driver);
    }

    public void usernameSetValue(String oldName, String newName) {
        oldNameField.sendKeys(oldName);
        newNameField.sendKeys(newName);
        submit.click();
    }

    public boolean checkResult(String operationName, String userName) {
        String xpathResult = "//div[@id='changeUsernameResult']";
        return driver.findElement(By.xpath(xpathResult + "//p[1]")).getText().equals(operationName) &&
                driver.findElement(By.xpath(xpathResult + "//p[2]")).getText().equals(userName);
    }

    public boolean checkResultWithError() {
        String error = "Ошибка: ";
        return result.getText().equals(error + "Имя пользователя не может быть пустым") ||
                result.getText().equals(error + "Пользователь не найден") ||
                result.getText().equals(error + "Пользователю не принадлежит это имя") ||
                result.getText().equals(error + "Имя пользователя должно быть от 4 до 20 символов") ||
                result.getText().equals(error + "Имя пользователя уже занято");
    }
}
