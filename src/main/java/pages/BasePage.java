package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    @FindBy(css = "a[href='/home']")
    private WebElement backToHome;
    @FindBy(css = "a[href='/logout']")
    private WebElement logout;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logout.click();
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickBackToHome() {
        backToHome.click();
    }
}