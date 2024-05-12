package ru.praktikum_services.qa_scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {

    private final WebDriver driver;
    private final By headerOrderButton = By.xpath(".//div[contains(@class,'Header_Nav')]/button[text()='Заказать']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

}
