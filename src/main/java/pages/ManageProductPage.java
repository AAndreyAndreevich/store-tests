package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageProductPage extends BasePage {

    @FindBy(id = "storeId")
    private WebElement storeIdField;
    @FindBy(id = "productId")
    private WebElement productIdField;
    @FindBy(id = "count")
    private WebElement countField;
    @FindBy(css = "button[onclick=\"submitForm('BUY_PRODUCT')\"]")
    private WebElement buyButton;
    @FindBy(css = "button[onclick=\"submitForm('SELL_PRODUCT')\"]")
    private WebElement sellButton;
    @FindBy(xpath = "//div[@id='manageProductResult']")
    private WebElement textResult;

    public ManageProductPage(WebDriver driver) {
        super(driver);
    }

    public void enterStoreId(String text) {
        storeIdField.sendKeys(text);
    }

    public void enterProductId(String text) {
        productIdField.sendKeys(text);
    }

    public void enterCount(String text) {
        countField.sendKeys(text);
    }

    public void buyClick() {
        buyButton.click();
    }

    public void sellClick() {
        sellButton.click();
    }

    public boolean checkResult(String operation, String product, String count, String store, String account) {
        String xpathTextResult = "//div[@id='manageProductResult']";
        return driver.findElement(By.xpath(xpathTextResult+"//p[1]")).getText().equals(operation) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[2]")).getText().equals(product) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[3]")).getText().equals(count) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[5]")).getText().equals(store) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[6]")).getText().equals(account);
    }

    public boolean checkResultWithError() {
        String error = "Ошибка: ";
        return textResult.getText().equals(error + "Продукт не найден") ||
                textResult.getText().equals(error + "Магазин не найден") ||
                textResult.getText().equals(error + "Количество не может быть равно или меньше нуля") ||
                textResult.getText().equals(error + "Недостаточно средств на балансе для покупки") ||
                textResult.getText().equals(error + "Пользователю не принадлежит магазин") ||
                textResult.getText().contains("Превышена вместимость склада.") ||
                textResult.getText().contains(error + "Превышен лимит количества продукта.");
    }

}