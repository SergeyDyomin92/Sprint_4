package ru.praktikumservices.qascooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikumservices.qascooter.pom.QuestionsPage;
import ru.praktikumservices.qascooter.rules.BrowserRule;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsPageTests {
    private final int index;
    private final String question;
    private final String answer;

    public QuestionsPageTests(int index, String question, String answer) {
        this.index = index;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswersData() {
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    @Test
    public void checkMatchesOnDropDownWithQuestionsAndAnswers() {
        QuestionsPage questionsPage = new QuestionsPage(browserRule.getWebDriver())
                .scrollToQuestionByIndex(index)
                .clickQuestionByIndex(index);

        String actualQuestion = questionsPage.getQuestionTextByIndex(index);
        String actualAnswer = questionsPage.getAnswerTextByIndex(index);
        assertEquals(format("Вопрос под индексом %s должен совпадать с ожидаемым", index), question, actualQuestion);
        assertEquals(format("Ответ под индексом %s должен совпадать с ожидаемым", index), answer, actualAnswer);
    }
}