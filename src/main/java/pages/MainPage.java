package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css = "a[href='/inv/buyProductForm']")
    private WebElement buyProductPage;
    @FindBy(css = "a[href='/inv/sellProductForm']")
    private WebElement sellProductPage;
    @FindBy(css = "a[href='/inv/getAllProductsForm']")
    private WebElement getAllProductPage;
    @FindBy(css = "a[href='/store/createStoreForm']")
    private WebElement createStorePage;
    @FindBy(css = "a[href='/store/deleteStoreForm']")
    private WebElement deleteStorePage;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickToBuy() {
        buyProductPage.click();
    }

    public void clickToSell() {
        sellProductPage.click();
    }

    public void clickToGetAllProduct() {
        getAllProductPage.click();
    }

    public void clickToCreateStore() {
        createStorePage.click();
    }

    public void clickToDeleteStore() {
        deleteStorePage.click();
    }
}