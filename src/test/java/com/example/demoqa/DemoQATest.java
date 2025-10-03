package com.example.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DemoQATest extends BaseTest {



    // todo добавьте нужную аннотацию, чтобы у теста появилось описание в консоли при запуске.
    //  Пример: "Проверка заголовка страницы"
    @Test
    @DisplayName("Проверка заголовка страницы")
    void checkPageTitle() {
        String expectedTitle = "DEMOQA";
        String actualTitle = $("head title").getOwnText();
        Assertions.assertTrue(actualTitle.contains(expectedTitle),
                "Заголовок страницы должен содержать 'DEMOQA'");
    }

    // todo добавьте нужную аннотацию, чтобы тест не запускался
    @Test
    @Disabled
    void disabledTestExample() {
        Assertions.fail("Этот тест не должен запускаться!");
    }


    // todo Добавьте нужную аннотацию со значениями ниже, чтобы тест заработал
    //  "Elements", "Forms", "Alerts, Frame & Windows", "Widgets", "Interactions", "Book Store Application"

    @ValueSource(strings = {
            "Elements", "Forms", "Alerts, Frame & Windows", "Widgets", "Interactions", "Book Store Application"
    })
    @ParameterizedTest(name = "Проверка наличия карточки: {0}")
    void checkCardExists(String cardName) {
        ElementsCollection cards = $$("div.card-body");
        boolean isCardPresent = cards.stream().anyMatch(card -> card.$("h5").text().equals(cardName));
        Assertions.assertTrue(isCardPresent, "Карточка '" + cardName + "' должна присутствовать на странице");
    }

    // todo добавьте сюда нужную аннотацию, которая будет содержать значение "SINGLE"
    //  затем в консоли с помощью команды mvn test -Dgroups=SINGLE запустите тест
    //  Убедитесь что запустился только 1 тест
    @Tag("SINGLE")
    @Test
    void clickElementsCard() {
        SelenideElement elementsCard = $$("div.card-body").find(Condition.text("Elements"));
        elementsCard.click();

        Selenide.Wait().until(webDriver -> webDriver.getCurrentUrl().contains("/elements"));
        Assertions.assertTrue(
                Selenide.webdriver().driver().url().contains("/elements"),
                "URL должен содержать '/elements' после клика"
        );
    }
    //todo Добавьте нужную аннотацию со значениями ниже, чтобы тест заработал
    @CsvFileSource(resources = {"/test-data2.csv"}, numLinesToSkip = 0,delimiter = '|')
    @ParameterizedTest(name = "Проверка URL для карточки: {0} → должен содержать '{1}'")
    void checkCardNavigation(String cardName, String expectedUrlFragment) {
        // Находим карточку по названию и кликаем
        SelenideElement targetCard = $$("div.card-body").find(Condition.text(cardName));
        targetCard.click();

        // Проверяем, что URL содержит ожидаемый фрагмент
        String currentUrl = Selenide.webdriver().driver().url();
        Assertions.assertTrue(
                currentUrl.contains(expectedUrlFragment),
                String.format("URL '%s' должен содержать фрагмент '%s'", currentUrl, expectedUrlFragment)
        );
    }


    //todo Добавьте нужную аннотацию чтобы тест заработал
    @ParameterizedTest(name = "Проверка URL для карточки: {0} → должен содержать '{1}'")
    @MethodSource("albina")
    void checkCardNavigation(String cardName, PageUrl pageUrl) {
        // Находим карточку по названию и кликаем
        SelenideElement targetCard = $$("div.card-body").find(Condition.text(cardName));
        targetCard.click();

        // Проверяем, что URL содержит ожидаемый фрагмент
        String currentUrl = Selenide.webdriver().driver().url();
        Assertions.assertTrue(
                currentUrl.contains(pageUrl.getEndPoint()),
                String.format("URL '%s' должен содержать фрагмент '%s'", currentUrl, pageUrl)
        );
    }
    static Stream <Arguments> albina(){
        return Stream.of
                (Arguments.of("Elements",new PageUrl("https://demoqa.com/elements")),
                        Arguments.of("Forms",new PageUrl("https://demoqa.com/forms")),
                        Arguments.of("Alerts, Frame & Windows",new PageUrl("https://demoqa.com/alertsWindows")),
                        Arguments.of("Widgets",new PageUrl("https://demoqa.com/widgets")),
                        Arguments.of("interaction",new PageUrl("https://demoqa.com/interaction")));

    }


}
