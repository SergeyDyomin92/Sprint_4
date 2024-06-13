package ru.praktikumservices.qascooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

public class OrderPage {
    private final WebDriver driver;
    private final By orderPageRoot = By.id("root");
    private final By orderForm = By.xpath(".//div[div[text()='Для кого самокат']]");
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By destinationAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroAutocompleteField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final String metroItem = ".//div[contains(@class, 'Order_Text__2broi') and text()='%s']";
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//div[contains(@class,'Order_NextButton')]/button");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage orderPageShouldBeVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderPageRoot)));
        return this;
    }

    public OrderPage orderFormShouldBeVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderForm)));
        return this;
    }

    public String getMetroXPathByName(String name) {
        return format(metroItem, name);
    }

    public OrderPage fillOrderInformation(String firstName, String lastName, String destination, String metro, String phonenumber) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(destinationAddressField).sendKeys(destination);
        driver.findElement(metroAutocompleteField).click();
        WebElement element = driver.findElement(By.xpath(getMetroXPathByName(metro)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        driver.findElement(phoneNumberField).sendKeys(phonenumber);
        return this;
    }

    /**
     * Метод с помощью JS удаляет попап с оповещением о куки,перекрывающий кнопку "Далее" на форме создания заказа.
     */
    public OrderPage deleteCookiePopup() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector("div.App_CookieConsent__1yUIN"));
        js.executeScript("arguments[0].remove();", element);
        return this;
    }

    public OrderPage clickNextButton() {
        WebElement element = driver.findElement(nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return this;
    }
}
