package org.example.habrtests.pages;

import org.example.habrtests.utils.AllureLogger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.example.habrtests.utils.MyWait.myWait;

//URL страницы https://habr.com/ru/feed/
public class FeedPage {
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(FeedPage.class));
    WebDriver driver;

    @FindBy(xpath = "//li//a[contains(text(), 'Новости')]")
    private WebElement news;

    @FindBy(xpath = "//nav/a[contains(text(), 'Разработка')]")
    private WebElement headerButtonDevelopment;

    @FindBy(css = ".tm-section-name__text")
    private WebElement myFeed;

    @FindBy(css = ".tm-navigation-filters-spoiler__button")
    private WebElement myFeedDropDown;

    @FindBy(css = ".icon_dropdown-arrow")
    private WebElement myFeedDropDownIsClosed;

    @FindBy(css = ".icon_dropdown-arrow-rotated")
    private WebElement myFeedDropDownIsOpen;

    @FindBy(css = ".tm-user-notice__container")
    private WebElement myFeedNoticeContainer;

    @FindBy(xpath = "//div//a[contains(text(), 'Войдите')]")
    private WebElement signUp;

    @FindBy(xpath = "//div//a[contains(text(), 'зарегистрируйтесь')]")
    private WebElement signIn;

    @FindBy(css = ".tm-button_color-christi")
    private WebElement applyButton;

    @FindBy(css = ".my-feed-filter__types .selection-label")
    private List<WebElement> checkboxesLabelList;

    @FindBy(xpath = "//div//legend[contains(text(), 'Уровень сложности')]")
    private WebElement difficultLevel;

    @FindBy(xpath = "//fieldset[legend[text()='Уровень сложности']]//label[span[text()='Все']]/span")
    private WebElement buttonAllInFieldDifficultLevel;

    @FindBy(xpath = "//fieldset[legend[text()='Уровень сложности']]/div/label/span")
    private List<WebElement> fourButtonFieldDifficultLevel;

    @FindBy(xpath = "//div/h2[contains(text(),'Новости')]")
    private WebElement chapterNewsHeader;

    @FindBy(css = ".tm-header__logo-wrap")
    private WebElement logoHabr;

    public void clickDevelopment() {
        log.info("Клик по кнопке-заголовку Разработка");
        headerButtonDevelopment.click();
    }

    public void clickNewsFooterButton() {
        log.info("Клик по кнопке-заголовку");
        news.click();
    }

    public boolean checkMyFeed() {
        log.info("Проверяем отображение раздела Моя лента");
        return myFeed.isDisplayed();
    }

    public boolean myFeedDropDownCheck() {
        log.info("Проверяем доступность дроп даун меню Настройка ленты раздела Моя лента");
        return myFeedDropDown.isEnabled();
    }

    public boolean myFeedDropDownIsClosedCheck() {
        log.info("Проверяем, что дроп даун меню закрыто");
        return myFeedDropDownIsClosed.isDisplayed();
    }

    public boolean myFeedClickAndCheckOpen() {
        log.info("Клик по дроп даун меню");
        myFeedDropDown.click();
        log.info("Проверяем, что дроп даун меню раскрылось");
        return myFeedDropDownIsOpen.isDisplayed();
    }

    public boolean openThanClosedMyFeed() {
        log.info("Проверяем, что дроп даун меню закрыто");
        myFeedDropDownIsClosed.isDisplayed();
        log.info("Клик по дроп даун меню");
        myFeedDropDown.click();
        log.info("Проверяем, что дроп даун меню раскрылось");
        myFeedDropDownIsOpen.isDisplayed();
        log.info("Клик по дроп даун меню");
        myFeedDropDown.click();
        log.info("Проверяем, что дроп даун меню закрылось");
        return myFeedDropDownIsClosed.isDisplayed();
    }

    public boolean myFeedDropDownClickAndNoticeContainerCheck() {
        log.info("Клик по дроп даун меню");
        myFeedDropDown.click();
        log.info("Проверяем наличие плашки с уведомлением");
        myWait(3).visible(myFeedNoticeContainer);
        return myFeedNoticeContainer.isDisplayed();
    }

    public String getMessageFromMyFeedNoticeContainer() {
        log.info("Получаем текст сообщения");
        return myFeedNoticeContainer.getText();
    }

    public boolean signUpAndSignInCheck() {
        log.info("Проверяем кликабельность \"Войдите\" и \"зарегистрируйтесь\"");
        return signUp.isEnabled() && signIn.isEnabled();
    }

    public void clickOnSignUp() {
        log.info("Клик по слову \"Войдите\"");
        signUp.click();
    }

    public void clickOnSignIn() {
        log.info("Клик поп слову \"зарегистрируйтесь\"");
        signIn.click();
    }

    public boolean applyButtonCheck() {
        log.info("Проверяем доступность кнопки подтверждения");
        return applyButton.isEnabled();
    }

    public int checkboxesQuantityCheck() {
        log.info("Проверяем кол-во чекбоксов");
        return checkboxesLabelList.size();
    }

    public boolean checkboxesIsEnabled() {
        log.info("Проверяем доступность чекбоксов");
        for (WebElement check : checkboxesLabelList) {
            if (!check.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public boolean invisibleDifficultLevelAfterClickCheckboxArticles() {
        log.info("Провереям видимость поля Уровень сложности");
        difficultLevel.isDisplayed();
        log.info("Кликаем по чекбоксу Статьи");
        checkboxesLabelList.get(0).click();
        log.info("Проверяем, что поле Уровень сложности исчезло");
        try {
            difficultLevel.isDisplayed();
        } catch (NoSuchElementException e) {
            log.info("Поле исчезло");
        }
        return false;
    }

    public String getColorButtonAllDefault() {
        log.info("Проверяем что кнопка выбрана по умолчанию");
        return buttonAllInFieldDifficultLevel.getCssValue("background-color");
    }

    public List<String> getTextFromButtonInFieldDifficultLevel() {
        List<String> actual = new ArrayList<>();
        log.info("Получаем текст из элементов");
        for (WebElement web : fourButtonFieldDifficultLevel) {
            log.info(web.getText());
            actual.add(web.getText());
        }
        log.info("Сверяем текст с ожидаемым");
        return actual;
    }

    public boolean checkChapterNews() {
        log.info("Проверяем наличие раздела новости");
        return chapterNewsHeader.isDisplayed();
    }

    public void clickOnHabrButtonAndCheckFeedPage() {
        log.info("Клик по кнопке лого Хабр");
        logoHabr.click();
        log.info("Ждем пока отобразится дроп даун меню на главной странице");
        myWait(5).visible(myFeedDropDown);
    }

    public FeedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
