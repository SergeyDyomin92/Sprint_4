package ru.praktikumservices.qascooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

public class RentPage {
    private WebDriver driver;
    private final By rentForm = By.xpath(".//div[div[text()='Про аренду']]");
    private final By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentPeriodSelector = By.xpath(".//div[text()='* Срок аренды']");
    private final String rentPeriodOption = ".//div[@class='Dropdown-option' and text()='%s']";
    private final String colourScooterCheckbox = ".//div[@class='Order_Checkboxes__3lWSI']//label[text()='%s']/input";
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By sendOrderButton = By.xpath(".//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public RentPage rentFormShouldBeVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(rentForm)));
        return this;
    }

    public RentPage setDeliveryDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String deliveryDate = LocalDate.now().plusDays(1).format(formatter);
        driver.findElement(deliveryDateField).sendKeys(deliveryDate);
        driver.findElement(deliveryDateField).sendKeys(Keys.ENTER);
        return this;
    }

    public String getRentPeriodOptionByText(String option) {
        return format(rentPeriodOption, option);
    }

    public String getElementOfColourScooterCheckbox(String name) {
        return format(colourScooterCheckbox, name);
    }

    public RentPage setColourCheckbox(String colour) {
        driver.findElement(By.xpath(getElementOfColourScooterCheckbox(colour))).click();
        return this;
    }


    public RentPage fillRentInformation(String period, String colour, String comment) {
        setDeliveryDay();
        driver.findElement(rentPeriodSelector).click();
        driver.findElement(By.xpath(getRentPeriodOptionByText(period))).click();
        setColourCheckbox(colour);
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    public RentPage sendOrder() {
        ModalWindows modalWindows = new ModalWindows(driver);
        driver.findElement(sendOrderButton).click();
        modalWindows.confirmModalWindowShouldBeVisible();
        modalWindows.clickAgreeButtonInModalWindow();
        return this;
    }


}
