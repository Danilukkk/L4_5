package org.example.habrtests.tests;

import org.example.habrtests.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedPageTest extends BaseTest {
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
    @DisplayName(value = "Редирект на страницу новости")
    public void newsRedirect() {
        feedPage.clickNewsFooterButton();
        assertFalse(newsPage.checkNewsButtonIsActive(), "newsPage не найден");
    }

    @Test
    @DisplayName("Присутствие раздела Моя лента в верхней части главной странице")
    public void myFeed() {
        assertTrue(feedPage.checkMyFeed(), "Раздел моя лента отсутствует");
    }

    @Test
    @DisplayName("Присутствует раздел Новости на главной странице")
    public void chapterNews() {
        assertTrue(feedPage.checkChapterNews(), "Раздел отсутствует");
    }

}
