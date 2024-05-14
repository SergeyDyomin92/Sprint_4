package ru.praktikumservices.qascooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.qascooter.pom.*;
import ru.praktikumservices.qascooter.rules.BrowserRule;
import ru.praktikumservices.qascooter.utils.Utils;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String period;
    private final String colour;
    private final String comment;

    public OrderTests(String firstName, String lastName, String address, String metro, String phoneNumber, String period, String colour, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDataForOrder() {
        Utils utils = new Utils();
        return new Object[][]{
                {utils.firstName,//имя
                        utils.lastName,//фамилия
                        utils.address,//адрес
                        "Технопарк",//метро
                        utils.phoneNumber,//телефон
                        "сутки",//период
                        "серая безысходность",//цвет
                        "Заказ создан автотестом"},//комментарий
                {utils.firstName,//имя
                        utils.lastName,//фамилия
                        utils.address,//адрес
                        "Черкизовская", //метро
                        utils.phoneNumber,//телефон
                        "двое суток",//период
                        "чёрный жемчуг",//цвет
                        "Заказ создан автотестом"}//комментарий
        };
    }

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void checkOrderIsCreatedByHeaderButtonFlow() {
        Header header = new Header(browserRule.getWebDriver());
        OrderPage orderPage = new OrderPage(browserRule.getWebDriver());
        RentPage rentPage = new RentPage(browserRule.getWebDriver());
        ModalWindows modalWindows = new ModalWindows(browserRule.getWebDriver());

        header.clickHeaderOrderButton();

        orderPage.orderPageShouldBeVisible()
                .orderFormShouldBeVisible()
                .fillOrderInformation(firstName,
                        lastName,
                        address,
                        metro,
                        phoneNumber)
                .deleteCookiePopup()
                .clickNextButton();

        rentPage.rentFormShouldBeVisible()
                .setDeliveryDay()
                .fillRentInformation(period,
                        colour,
                        comment)
                .sendOrder();

        assertTrue("Сообщение об успешном создании заказа должно быть отображено", modalWindows.checkSuccessMessageShouldBeExist());

    }

    @Test
    public void checkOrderIsCreatedByRoadMapButtonFlow() {
        RoadMapPage roadMapPage = new RoadMapPage(browserRule.getWebDriver());
        OrderPage orderPage = new OrderPage(browserRule.getWebDriver());
        RentPage rentPage = new RentPage(browserRule.getWebDriver());
        ModalWindows modalWindows = new ModalWindows(browserRule.getWebDriver());

        roadMapPage.scrollToCreateOrderButtonInRoadMapPage()
                .clickRoadMapPageButton();

        orderPage.orderPageShouldBeVisible()
                .orderFormShouldBeVisible()
                .fillOrderInformation(firstName,
                        lastName,
                        address,
                        metro,
                        phoneNumber)
                .deleteCookiePopup()
                .clickNextButton();

        rentPage.rentFormShouldBeVisible()
                .setDeliveryDay()
                .fillRentInformation(period,
                        colour,
                        comment)
                .sendOrder();

        assertTrue("Сообщение об успешном создании заказа должно быть отображено", modalWindows.checkSuccessMessageShouldBeExist());

    }
}
