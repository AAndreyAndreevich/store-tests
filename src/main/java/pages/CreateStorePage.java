package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateStorePage extends BasePage {

    @FindBy(id = "storeName")
    private WebElement storeNameField;
    @FindBy(css = "button[onclick]")
    private WebElement submit;

    public CreateStorePage(WebDriver driver) {
        super(driver);
    }

    public void enterStoreName(String text) {
        storeNameField.sendKeys(text);
    }

    public void createStoreClick() {
        submit.click();
    }
}
