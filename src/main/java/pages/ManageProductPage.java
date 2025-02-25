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

    private final String xpathTextResult = "//div[@id='manageProductResult']";

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
        return driver.findElement(By.xpath(xpathTextResult+"//p[1]")).getText().equals(operation) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[2]")).getText().equals(product) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[3]")).getText().equals(count) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[5]")).getText().equals(store) &&
                driver.findElement(By.xpath(xpathTextResult+"//p[6]")).getText().equals(account);
    }

    public boolean checkResultWithError() {
        String error = "Ошибка: ";
        return driver.findElement(By.xpath(xpathTextResult)).getText().equals(error + "Продукт не найден") ||
                driver.findElement(By.xpath(xpathTextResult)).getText().equals(error + "Магазин не найден") ||
                driver.findElement(By.xpath(xpathTextResult)).getText().equals(error + "Количество не может быть равно или меньше нуля") ||
                driver.findElement(By.xpath(xpathTextResult)).getText().equals(error + "Недостаточно средств на балансе для покупки") ||
                driver.findElement(By.xpath(xpathTextResult)).getText().equals(error + "Пользователю не принадлежит магазин") ||
                driver.findElement(By.xpath(xpathTextResult)).getText().contains("Превышена вместимость склада.") ||
                driver.findElement(By.xpath(xpathTextResult)).getText().contains(error + "Превышен лимит количества продукта.");
    }

}