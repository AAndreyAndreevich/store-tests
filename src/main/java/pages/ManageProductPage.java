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
        String xpath = "//div[@id='manageProductResult']//p";
        return driver.findElement(By.xpath(xpath+"[1]")).getText().equals(operation) &&
                driver.findElement(By.xpath(xpath+"[2]")).getText().equals(product) &&
                driver.findElement(By.xpath(xpath+"[3]")).getText().equals(count) &&
                driver.findElement(By.xpath(xpath+"[5]")).getText().equals(store) &&
                driver.findElement(By.xpath(xpath+"[6]")).getText().equals(account);
    }

}