package ru.praktikumservices.qascooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionsPage {
    private final WebDriver driver;
    private final By questionAccordionItems = By.xpath(".//div[@class='accordion__heading']");
    private final By answerAccordionItems = By.xpath(".//div[@class='accordion__panel']");


    public QuestionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public QuestionsPage scrollToQuestionByIndex(int i) {
        WebElement element = driver.findElements(questionAccordionItems).get(i);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public QuestionsPage clickQuestionByIndex(int i) {
        driver.findElements(questionAccordionItems).get(i).click();
        return this;
    }

    public String getQuestionTextByIndex(int i) {
        return driver.findElements(questionAccordionItems).get(i).getText();
    }

    public String getAnswerTextByIndex(int i) {
        return driver.findElements(answerAccordionItems).get(i).getText();
    }
}
