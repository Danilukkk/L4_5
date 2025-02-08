package org.example.habrtests.tests;

import org.example.habrtests.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DropDownFeedSettingsOnFeedPageTest extends BaseTest {
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
    @DisplayName("Доступно дроп даун меню Настройки ленты в блоке Моя лента в верхней части главной странице")
    public void dropDownMyFeed() {
        assertTrue(feedPage.myFeedDropDownCheck(), "Дроп даун меню не доступно");
    }

    @Test
    @DisplayName("Начальное состояние дроп даун меню Настройки ленты в блоке Моя лента в верхней части главной странице - закрыто")
    public void startPositionDropDownMyFeed() {
        assertTrue(feedPage.myFeedDropDownIsClosedCheck());
    }

    @Test
    @DisplayName("Можно раскрыть дроп даун меню Настройки ленты в блоке Моя лента на главной странице")
    public void openDropDownMyFeed() {
        assertTrue(feedPage.myFeedClickAndCheckOpen(), "Дроп даун меню не раскрылось");
    }

    @Test
    @DisplayName("Можно раскрыть, а затем закрыть дроп даун меню Настройки ленты в блоке Моя лента на главной странице")
    public void dropDownMyFeedOpenAndClosed() {
        assertTrue(feedPage.openThanClosedMyFeed(), "Меню осталось открытым");
    }

    @Test
    @DisplayName("Есть плашка с уведомлением для неавторизованных пользователей дроп даун меню Настройки ленты в блоке Моя лента на главной странице")
    public void dropDownMyFeedNoticeContainer() {
        assertTrue(feedPage.myFeedDropDownClickAndNoticeContainerCheck(), "Плашка с уведомлением отсутствует");
    }

    @Test
    @DisplayName("Кнопка \"Применить\" в плашке-уведомлении в дроп даун меню Настройки ленты в блоке Моя лента на главной странице недоступна для неавторизованных пользователей")
    public void applyButtonDisabled() {
        assertFalse(feedPage.applyButtonCheck(), "Кнопка доступна");
    }

    @Test
    @DisplayName("Есть три чекбокса в поле Тип публикации в дроп даун меню Настройка ленты в блоке Моя лента на главной странице")
    public void thereAreThreeCheckboxes() {
        feedPage.myFeedClickAndCheckOpen();
        assertEquals(feedPage.checkboxesQuantityCheck(), 3, "Кол-во чекбоксов не равно 3");
    }

    @Test
    @DisplayName("Чекбоксы в поле Тип публикации в дроп даун меню Настройка ленты в блоке Моя лента на главной странице доступны для клика")
    public void checkboxesReadyForClick() {
        feedPage.myFeedClickAndCheckOpen();
        assertTrue(feedPage.checkboxesIsEnabled(), "Чекбокс(сы) недоступны для клика");
    }

    @Test
    @DisplayName("Скрывается поле Уровень сложности после клика по чекбоксу Статьи в поле Тип публикации в дроп даун меню Настройка ленты в блоке Моя лента на главной странице")
    public void noSeeDifficultLevelAfterClick() {
        feedPage.myFeedClickAndCheckOpen();
        assertFalse(feedPage.invisibleDifficultLevelAfterClickCheckboxArticles(), "Поле Уровень сложности не было скрыто");
    }

    @Test
    @DisplayName("Кнопка Все в поле Уровень сложности в дроп даун меню Настройка ленты в блоке Моя лента на главной странице выбрана по умолчанию")
    public void buttonAllDefaultSelected() {
        feedPage.myFeedClickAndCheckOpen();
        String exp = "rgba(76, 183, 235, 1)";
        assertEquals(exp, feedPage.getColorButtonAllDefault(), "Поле не выбрано по умолчанию");
    }

    @Test
    @DisplayName("Текст в плашке-уведомлении для неавторизованных пользователей \"Войдите или зарегистрируйтесь, чтобы настроить фильтры\" в дроп даун меню Настройки ленты в блоке Моя лента на главной странице")
    public void messageInDropDownMyFeedNoticeContainer() {
        feedPage.myFeedDropDownClickAndNoticeContainerCheck();
        assertEquals(feedPage.getMessageFromMyFeedNoticeContainer(), "Войдите или зарегистрируйтесь, чтобы настроить фильтры", "Текст не совпадает");
    }

    //Почему-то не получается выгребать текст из этих элементов, буду рад если подскажете
    @Test
    @DisplayName("В поле Уровень сложности в дроп даун меню Настройка ленты в блоке Моя лента на главной странице, есть 4 кнопки с текстом: Все, Просто, Средний, Сложно")
    public void fieldDifficultLevelHasButtonWithExpectedText() {
        List<String> exp = Arrays.asList("Все", "Простой", "Средний", "Сложный");
        assertEquals(feedPage.getTextFromButtonInFieldDifficultLevel(), exp, "Текст не совпадает");
    }

    @Test
    @DisplayName("Слова \"Войдите\" и \"Зарегистрируйтесь\" в плашке-уведомлении для неавторизованных пользователей в дроп даун меню Настройки ленты в блоке Моя лента на главной странице кликабельны")
    public void wordSignUpAndSignInClickable() {
        feedPage.myFeedDropDownClickAndNoticeContainerCheck();
        assertTrue(feedPage.signUpAndSignInCheck(), "Один или оба элемента не кликабельны");
    }


    @Test
    @DisplayName("Редирект на страницу авторизации после клика по слову \"Войдите\" в плашке-уведомлении для неавторизованных пользователей в дроп даун меню Настройки ленты в блоке Моя лента на главной странице")
    public void redirectOnSignUpPage() {
        feedPage.myFeedDropDownClickAndNoticeContainerCheck();
        feedPage.clickOnSignUp();
        assertTrue(signUpPage.headerSignUpCheck(), "Заголовок отсутствует");
    }

    @Test
    @DisplayName("Редирект на страницу регистрации после клика по слову \"зарегистрируйтесь\" в плашке-уведомлении для неавторизованных пользователей в дроп даун меню Настройки ленты в блоке Моя лента на главной странице")
    public void redirectOnSignInPage() {
        feedPage.myFeedDropDownClickAndNoticeContainerCheck();
        feedPage.clickOnSignIn();
        assertTrue(signInPage.headerSignInCheck(), "Заголовок отсутствует");
    }

}

