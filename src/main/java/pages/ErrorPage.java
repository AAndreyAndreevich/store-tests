package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage extends BasePage {

    @FindBy(xpath = "//div[@class='alert alert-danger mt-4']/span")
    private WebElement errorMessage;

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    public String getErrorText() {
        return errorMessage.getText();
    }
}