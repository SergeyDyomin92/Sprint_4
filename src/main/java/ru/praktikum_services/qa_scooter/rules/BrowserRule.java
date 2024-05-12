package ru.praktikum_services.qa_scooter.rules;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum_services.qa_scooter.pom.HomePage;

public class BrowserRule  extends ExternalResource {
    private WebDriver driver;

    protected void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        HomePage homePage = new HomePage(driver);
        homePage.open()
                .homePageShouldBeVisible();
    }

    protected void after() {
        driver.quit();
    }

    public WebDriver getWebDriver() {
        return driver;
    }
}
