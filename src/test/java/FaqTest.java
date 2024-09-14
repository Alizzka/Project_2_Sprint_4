import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class FaqTest {

    private WebDriver driver;
    private MainPage generalPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        generalPage = new MainPage(driver);  // Инициализация MainPage
    }

    @Test
    public void checkFirstQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                generalPage.getAnswer(MainPage.Question_1));
    }

    @Test
    public void checkSecondQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим.",
                generalPage.getAnswer(MainPage.Question_2));
    }

    @Test
    public void checkThirdQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                generalPage.getAnswer(MainPage.Question_3));
    }

    @Test
    public void checkFourthQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                generalPage.getAnswer(MainPage.Question_4));
    }

    @Test
    public void checkFifthQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Пока что нет! Но если что-то срочное — всегда можно позвонить " +
                        "в поддержку по красивому номеру 1010.",
                generalPage.getAnswer(MainPage.Question_5));
    }

    @Test
    public void checkSixthQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                generalPage.getAnswer(MainPage.Question_6));
    }

    @Test
    public void checkSeventhQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Да, пока самокат не привезли. Штрафа не будет, " +
                        "объяснительной записки тоже не попросим. Все же свои.",
                generalPage.getAnswer(MainPage.Question_7));
    }

    @Test
    public void checkEighthQuestion() {
        generalPage.scrollMainPage();
        assertEquals("Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                generalPage.getAnswer(MainPage.Question_8));
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


//говнокод
/*package tests.order;

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
}*/

