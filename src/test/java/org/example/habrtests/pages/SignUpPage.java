package org.example.habrtests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpPage {
    private static final Logger log = LoggerFactory.getLogger(SignUpPage.class);
    WebDriver driver;

    @FindBy(xpath = "//div//div[contains(text(), 'Вход')]")
    private WebElement headerSignUp;

    public boolean headerSignUpCheck() {
        log.info("Проверяем наличие заголовка на странице");
        return headerSignUp.isDisplayed();
    }

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
