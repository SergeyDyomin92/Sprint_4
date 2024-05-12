package ru.praktikum_services.qa_scooter.qa_scooter;

import org.junit.Rule;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.pom.*;
import ru.praktikum_services.qa_scooter.rules.BrowserRule;

import static org.junit.Assert.assertTrue;
import static ru.praktikum_services.qa_scooter.utils.Utils.getRandomCyrillicText;
import static ru.praktikum_services.qa_scooter.utils.Utils.getRandomPhone;

public class OrderTests {

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
                .fillOrderInformation(getRandomCyrillicText(),
                        getRandomCyrillicText(),
                        "Москва, улица Тестовая 1, ресторан Гусь",
                        "Технопарк",
                        getRandomPhone())
                .deleteCookiePopup()
                .clickNextButton();

        rentPage.rentFormShouldBeVisible()
                .setDeliveryDay()
                .fillRentInformation("сутки",
                        "серая безысходность",
                        getRandomCyrillicText())
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
                .fillOrderInformation(getRandomCyrillicText(),
                        getRandomCyrillicText(),
                        "Москва, ул. Тюменская 5",
                        "Черкизовская",
                        getRandomPhone())
                .deleteCookiePopup()
                .clickNextButton();

        rentPage.rentFormShouldBeVisible()
                .setDeliveryDay()
                .fillRentInformation("двое суток", "чёрный жемчуг", "Тестовый комментарий")
                .sendOrder();

        assertTrue("Сообщение об успешном создании заказа должно быть отображено", modalWindows.checkSuccessMessageShouldBeExist());

    }
}
