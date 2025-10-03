package com.example.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeAll
    static void setUpAll() {
        // Настройка один раз перед всеми тестами
        Configuration.browser = "edge";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadTimeout = 120000;
        System.out.println(">>> [BeforeAll] Браузер настроен глобально.");
    }

    @BeforeEach
    void setUpEach() {
        // Открытие главной страницы перед каждым тестом
        Selenide.open("https://demoqa.com/");
        System.out.println(">>> [BeforeEach] Открыта главная страница DemoQA.");
    }

    @AfterEach
    void tearDownEach() {
        // Закрытие браузера после каждого теста
        Selenide.closeWebDriver();
        System.out.println(">>> [AfterEach] Браузер закрыт.");
    }

    @AfterAll
    static void tearDownAll() {
        // Опционально: можно очистить кэш, отчеты и т.д.
        System.out.println(">>> [AfterAll] Все тесты завершены. Глобальная очистка.");
    }
}
