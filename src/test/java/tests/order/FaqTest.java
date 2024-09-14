package tests.order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class FaqTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Увеличенное время ожидания
        mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkFaqDropdown() {
        // Ожидание видимости заголовка страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[4]")));

        // Нажать на первый вопрос в FAQ
        mainPage.openFaqQuestion(0);

        // Добавьте проверки видимости текста, если нужно
        // Например:
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[5]/div[1]")));
        assertTrue(answer.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

