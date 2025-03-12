package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangeStoreNamePage extends BasePage {

    @FindBy(id = "oldName")
    private WebElement oldNameField;
    @FindBy(id = "newName")
    private WebElement newNameField;
    @FindBy(id = "changeStoreNameResult")
    private WebElement result;
    @FindBy(css = "button[onclick]")
    private WebElement submit;

    public ChangeStoreNamePage(WebDriver driver) {
        super(driver);
    }

    public void storeNameSetValue(String oldName, String newName) {
        oldNameField.sendKeys(oldName);
        newNameField.sendKeys(newName);
        submit.click();
    }

    public boolean checkResult(String operationName, String userName, String storeName) {
        String xpathResult = "//div[@id='changeStoreNameResult']";
        return driver.findElement(By.xpath(xpathResult + "//p[1]")).getText().equals(operationName) &&
                driver.findElement(By.xpath(xpathResult + "//p[2]")).getText().equals(userName) &&
                driver.findElement(By.xpath(xpathResult + "//p[3]")).getText().equals(storeName);
    }

    public boolean checkResultWithError(String name) {
        String error = "Ошибка: ";
        return result.getText().equals(error + "Название магазина не может быть пустым") ||
                result.getText().contains(error + "Магазин с названием '"+name+"' существует") ||
                result.getText().equals(error + "Пользователь не найден") ||
                result.getText().equals(error + "Название магазина не может быть пустым") ||
                result.getText().equals(error + "Пользователь не найден") ||
                result.getText().equals(error + "Название не может совпадать") ||
                result.getText().equals(error + "Пользователю не принадлежит магазин") ||
                result.getText().equals(error + "Название магазина должно быть от 3 до 30 символов") ||
                result.getText().contains(error + "Магазин с названием '"+name+"' не найден");
    }
}