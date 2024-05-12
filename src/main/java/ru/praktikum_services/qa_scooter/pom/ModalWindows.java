package ru.praktikum_services.qa_scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalWindows {

    private final WebDriver driver;
    private final By confirmModalWindow = By.xpath(".//div[contains(@class, 'Order_Modal')][div[contains(@class,'Order_ModalHeader') and text()='Хотите оформить заказ?']]");
    private final By agreeButtonInConfirmModalWindow = By.xpath(".//div[contains(@class, 'Order_Modal')]//button[text()='Да']");
    private final By orderIsCreatedPopupText = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");

    public ModalWindows(WebDriver driver) {
        this.driver = driver;
    }


    public void confirmModalWindowShouldBeVisible() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(confirmModalWindow)));
    }

    public void clickAgreeButtonInModalWindow(){
        driver.findElement(agreeButtonInConfirmModalWindow).click();
    }


    public boolean checkSuccessMessageShouldBeExist() {
        return !driver.findElements(orderIsCreatedPopupText).isEmpty();
    }

}
