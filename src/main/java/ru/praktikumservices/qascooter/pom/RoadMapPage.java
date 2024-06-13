package ru.praktikumservices.qascooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RoadMapPage {
    private final WebDriver driver;

    private final By roadMapPageButton = By.xpath(".//div[contains(@class,'Home_ThirdPart')]//button[text()='Заказать']");


    public RoadMapPage(WebDriver driver) {
        this.driver = driver;
    }

    public RoadMapPage scrollToCreateOrderButtonInRoadMapPage() {
        WebElement element = driver.findElement(roadMapPageButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public RoadMapPage clickRoadMapPageButton() {
        driver.findElement(roadMapPageButton).click();
        return this;
    }
}
