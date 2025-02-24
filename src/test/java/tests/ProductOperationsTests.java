package tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import utils.BaseTest;

public class ProductOperationsTests extends BaseTest {


    private static final Logger log = LoggerFactory.getLogger(ProductOperationsTests.class);
    private MainPage mainPage;
    private LoginAndRegistrationPage loginAndRegPage;
    private ManageProductPage managePage;
    private GetAllProductsPage getProductsPage;
    private CreateStorePage createStorePage;

    private final String URL = "http://localhost:8080/";

    private void setSettingDriver() {
        log.info("Подключение");
        driver.get(URL);
        mainPage = new MainPage(driver);
        loginAndRegPage = new LoginAndRegistrationPage(driver);
        managePage = new ManageProductPage(driver);
        getProductsPage = new GetAllProductsPage(driver);
        createStorePage = new CreateStorePage(driver);
    }
}