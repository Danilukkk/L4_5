package org.example.habrtests.pages;

import org.example.habrtests.utils.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DevelopmentPage {
    private WebDriver driver;
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(DevelopmentPage.class));

    @FindBy(xpath = "//span/a[contains(text(), 'Авторы')]")
    private WebElement authorsHeaderSpan;

    @FindBy(css = ".tm-navigation-sorting div:first-child")
    private List<WebElement> nameList;

    public void clickAuthors() {
        authorsHeaderSpan.click();
    }

    public String getName() {
        log.info("Наличие таблицы Имя на вкладке Авторы");
        return nameList.get(0).getText();
    }

    public DevelopmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
