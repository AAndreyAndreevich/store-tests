package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;
    @FindBy(css = "a[href='/home']")
    private WebElement homeButton;
    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToHome() {
        homeButton.click();
    }

    public void clickLogout() {
        logoutButton.click();
    }
}