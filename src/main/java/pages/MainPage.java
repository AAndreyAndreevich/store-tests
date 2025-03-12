package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css = "a[href='/inv/manageProductForm']")
    private WebElement manageButton;
    @FindBy(css = "a[href='/inv/getAllProductsForm']")
    private WebElement getProductsButton;
    @FindBy(css = "a[href='/store/createStoreForm']")
    private WebElement createStoreButton;
    @FindBy(css = "a[href='/store/changeStoreNameForm']")
    private WebElement changeStoreNameButton;
    @FindBy(css = "a[href='/changeName']")
    private WebElement changeUsernameButton;
    @FindBy(css = "a[href='/changePassword']")
    private WebElement changePasswordButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickToManage() {
        manageButton.click();
    }

    public void clickToProductsList() {
        getProductsButton.click();
    }

    public void clickToCreateStore() {
        createStoreButton.click();
    }

    public void clickToChangeUsername() {
        changeUsernameButton.click();
    }

    public void clickToChangeStoreName() {
        changeStoreNameButton.click();
    }

    public void clickToChangePassword() {
        changePasswordButton.click();
    }

}