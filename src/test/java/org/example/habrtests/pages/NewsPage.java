package org.example.habrtests.pages;

import org.example.habrtests.utils.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NewsPage {
    private final AllureLogger log = new AllureLogger(LoggerFactory.getLogger(NewsPage.class));
    WebDriver driver;

    @FindBy(xpath = "//span/a[contains(text(), 'Новости') " + "and contains(@class,'tm-tabs__tab-link_active' )]")
    private List<WebElement> newsPage;

    public boolean checkNewsButtonIsActive() {
        log.info("Проверяем активность кнопки новости");
        return newsPage.isEmpty();
    }

    public NewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
