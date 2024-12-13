package org.example.l4_5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://habr.com/ru/feed/");


    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Редирект на страницу новости")
    public void news() {
        WebElement news = driver.findElement(By.xpath("//a[contains(text(), 'Новости')]"));
        news.click();

        List <WebElement> newsPage = driver.findElements(By.xpath("//span/a[contains(text(), 'Новости') and contains(@class,'tm-tabs__tab-link_active' )]"));

        assertFalse(newsPage.isEmpty(), "newsPage не найден" );
    }

    @Test
    @DisplayName("Редирект на страницу Авторы")
    public void authors(){
        WebElement allStreams = driver.findElement(By.xpath("//nav/a[contains(text(), 'Разработка')]"));
        allStreams.click();
        WebElement spanAuthors = driver.findElement(By.xpath("//span/a[contains(text(), 'Авторы')]"));
        spanAuthors.click();
        List <WebElement> nameList = driver.findElements(By.xpath("//div[contains(text(), 'Имя')]"));
        for (WebElement thisOne : nameList){
            assertEquals("Имя",thisOne.getText(), "Имя не найдено");
        }

    }






}
