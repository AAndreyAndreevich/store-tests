package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateStorePage extends BasePage {

    @FindBy(id = "storeName")
    private WebElement storeNameField;
    @FindBy(css = "button[onclick]")
    private WebElement submit;
    @FindBy(id = "createStoreResult")
    private WebElement result;

    public CreateStorePage(WebDriver driver) {
        super(driver);
    }

    public void enterStoreName(String text) {
        storeNameField.sendKeys(text);
    }

    public void createStoreClick() {
        submit.click();
    }

    public boolean checkResultWithError(String name) {
        return result.getText().equals("Ошибка: Магазин с названием '" + name + "' существует") ||
                result.getText().equals("Ошибка: Название магазина не может быть пустым");
    }
}
