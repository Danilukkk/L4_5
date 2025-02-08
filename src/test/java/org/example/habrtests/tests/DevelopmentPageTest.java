package org.example.habrtests.tests;

import org.example.habrtests.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DevelopmentPageTest extends BaseTest {
    private FeedPage feedPage;
    private NewsPage newsPage;
    private DevelopmentPage developmentPage;
    private SignUpPage signUpPage;
    private SignInPage signInPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        log.info("Переход на главную страницу Habr");
        getDriver().get("https://habr.com/ru/feed/");
        feedPage = new FeedPage(getDriver());
        newsPage = new NewsPage(getDriver());
        developmentPage = new DevelopmentPage(getDriver());
        signUpPage = new SignUpPage(getDriver());
        signInPage = new SignInPage(getDriver());
    }

    @Test
    @DisplayName("Редирект на вкладку Авторы со страницы Разработка")
    public void redirectFromAuthorsToDevelopment() {
        feedPage.clickDevelopment();
        developmentPage.clickAuthors();
        assertEquals("Имя", developmentPage.getName(), "Имя не найдено");
    }

    @DisplayName("Редирект на главную страницы со страницы Разработка по клику по лого Хабр в шапке сайта")
    public void reloadFeedPage() {
        feedPage.clickDevelopment();
        feedPage.clickOnHabrButtonAndCheckFeedPage();
    }

}
