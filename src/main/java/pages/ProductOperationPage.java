package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductOperationPage extends BasePage {

    @FindBy(id = "storeId")
    private WebElement storeIdField;
    @FindBy(id = "productId")
    private WebElement productIdField;
    @FindBy(id = "count")
    private WebElement countInput;

    public ProductOperationPage(WebDriver driver) {
        super(driver);
    }

    public void enterStoreId(String text) {
        storeIdField.sendKeys(text);
    }

    public void enterProductId(String text) {
        productIdField.sendKeys(text);
    }

    public void enterCount(String text) {
        countInput.sendKeys(text);
    }
}