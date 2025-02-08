package org.example.habrtests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInPage {
    private static final Logger log = LoggerFactory.getLogger(SignInPage.class);
    WebDriver driver;

    @FindBy(xpath = "//div//div[contains(text(), 'Регистрация')]")
    private WebElement headerSignIn;

    public boolean headerSignInCheck() {
        log.info("Проверяем наличие заголовка на странице");
        return headerSignIn.isDisplayed();
    }

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
