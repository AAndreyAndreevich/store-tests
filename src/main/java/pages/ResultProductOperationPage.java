package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultProductOperationPage extends BasePage {

    @FindBy(xpath = "//p[text()='Покупка успешно завершена!']")
    private WebElement successBuyMessage;
    @FindBy(xpath = "//p[text()='Продажа успешно завершена!']")
    private WebElement successSellMessage;
    @FindBy(xpath = "(//ul/li)[1]/span")
    private WebElement storeNameField;
    @FindBy(xpath = "(//ul/li)[2]/span")
    private WebElement productNameField;
    @FindBy(xpath = "(//ul/li)[3]/span")
    private WebElement countField;
    @FindBy(xpath = "(//ul/li)[4]/span")
    private WebElement balanceAfterOperationField;
    @FindBy(css = "a[href='/inv/buyProductForm']")
    private WebElement backToBuyForm;
    @FindBy(css = "a[href='/inv/sellProductForm']")
    private WebElement backToSellForm;

    public ResultProductOperationPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessText() {
        if (successBuyMessage.isDisplayed()) {
            return successBuyMessage.getText();
        } else if (successSellMessage.isDisplayed()) {
            return successSellMessage.getText();
        } else {
            return "Статус не определён";
        }
    }

    public String getStoreName() {
        return storeNameField.getText();
    }

    public String getProductName() {
        return productNameField.getText();
    }

    public String getCount() {
        return countField.getText();
    }

    public String getBalanceAfterOperation() {
        return balanceAfterOperationField.getText();
    }

    public void clickBackToBuyForm() {
        backToBuyForm.click();
    }

    public void clickBackToSellForm() {
        backToSellForm.click();
    }
}