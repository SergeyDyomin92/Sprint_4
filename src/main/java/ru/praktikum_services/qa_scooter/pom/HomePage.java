package ru.praktikum_services.qa_scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private final WebDriver driver;
    private final By homePageRoot = By.xpath(".//div[contains(@class,'Home_HomePage')]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage open() {
        driver.get(URL);
        return this;
    }

    public void homePageShouldBeVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(homePageRoot)));
    }

}
