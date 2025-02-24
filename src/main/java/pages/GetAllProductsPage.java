package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetAllProductsPage extends BasePage {

    @FindBy(id = "storeId")
    private WebElement store;
    @FindBy(css = "button[onclick='submitGetAllProductsForm()']")
    private WebElement submit;

    public GetAllProductsPage(WebDriver driver) {
        super(driver);
    }

    public void storeSetValue(String text) {
        store.sendKeys(text);
    }

    public void clickSubmit() {
        submit.click();
    }
}
