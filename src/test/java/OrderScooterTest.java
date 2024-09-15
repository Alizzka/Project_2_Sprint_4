import org.junit.Assert;
import pageobjects.PageOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class OrderScooterTest {

    private WebDriver driver;

    private PageOrder generalPage; // Инициализация PageOrder ниже!

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        generalPage = new PageOrder(driver);  // Инициализация PageOrder
    }

    @Test
    public void testOrderScooterOne() {
        enterDataFirstPageOrder("Галина", "Петровна", "Москва", 3, "+79667653344");
        enterDataSecondPageOrder("черный", "16.09.2024", 7, "Позвоните за 30 минут");
        Assert.assertTrue(generalPage.successfullyText());
    }

    private void enterDataSecondPageOrder(String цвет, String дата, int срок, String комментарий) {
        // Реализовать ввод данных на второй странице. Для теста Выше!
    }

    private void enterDataFirstPageOrder(String имя, String фамилия, String адрес, int номерКвартиры, String телефон) {
        // Реализовать ввод данных на первой странице. Для теста ниже!
    }

    @Test
    public void testOrderScooterTwo() {
        enterDataFirstPageOrder("Роман", "Максимов", "Москва", 15, "+79541112233");
        enterDataSecondPageOrder("серый", "18.10.2024", 5, "");
        Assert.assertTrue(generalPage.successfullyText());
    }

    @Test
    public void testOrderScooterThree() {
        enterDataFirstPageOrder("Петр", "Первый", "Санкт-Петербург", 10, "+75126778894");
        enterDataSecondPageOrder("черный", "27.09.2024", 3, "Оставить около двери");
        Assert.assertTrue(generalPage.successfullyText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}




