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

    private PageOrder generalPage;

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
        enterDataFirstPageOrder("Галина", "Петровна", "Луга", 3, "+79667653344");
        enterDataSecondPageOrder("черный", "16.09.2024", 7, "Позвоните за 30 минут");
        //Assert.assertTrue(successfullyText());
    }

    /*private boolean successfullyText() {
        // Здесь необходимо реализовать логику проверки успешного текста
        return true;
    }*/

    private void enterDataSecondPageOrder(String цвет, String дата, int срок, String комментарий) {
        // Реализовать ввод данных на второй странице
    }

    private void enterDataFirstPageOrder(String имя, String фамилия, String адрес, int номерКвартиры, String телефон) {
        // Реализовать ввод данных на первой странице
    }

    @Test
    public void testOrderScooterTwo() {
        enterDataFirstPageOrder("Роман", "Максимов", "Москва", 15, "+79541112233");
        enterDataSecondPageOrder("серый", "18.10.2024", 5, "");
        //Assert.assertTrue(successfullyText());
    }

    @Test
    public void testOrderScooterThree() {
        enterDataFirstPageOrder("Петр", "Первый", "Санкт-Петербург", 10, "+75126778894");
        enterDataSecondPageOrder("черный", "27.09.2024", 3, "Оставить около двери");
        //Assert.assertTrue(successfullyText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}


//говнокод
/*package tests.order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderScooterTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public String surname;

    @Parameterized.Parameter(2)
    public String address;

    @Parameterized.Parameter(3)
    public String phone;

    @Parameterized.Parameter(4)
    public String metroStation;

    @Parameterized.Parameter(5)
    public String deliveryDate;

    @Parameterized.Parameter(6)
    public String rentalPeriod;

    @Parameterized.Parameter(7)
    public String scooterColor;

    @Parameterized.Parameter(8)
    public String comment;

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Ленина", "89998887766", "Киевская", "31 марта", "сутки", "black", "Тестовый комментарий"},
                {"Мария", "Петрова", "ул. Пушкина", "89991234567", "Таганская", "1 апреля", "двое суток", "grey", "Комментарий для теста"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        closeCookieBannerIfPresent();
    }

    private void closeCookieBannerIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cookieBannerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rcc-confirm-button")));
            if (cookieBannerButton.isDisplayed()) {
                cookieBannerButton.click();
            }
        } catch (Exception e) {
            // Баннер не отображается
        }
    }

    @Test
    public void checkOrderFlow() {
        // Проверка заказа через верхнюю кнопку "Заказать"
        mainPage.clickOrderButtonTop();
        pauseFor(3);
        orderPage.fillOrderDetails(name, surname, address, phone);
        pauseFor(3);
        selectMetroStationWithWait(metroStation);
        pauseFor(3);
        orderPage.clickNextButton();
        pauseFor(3);
        orderPage.fillAdditionalOrderDetails(deliveryDate, rentalPeriod, scooterColor, comment);
        pauseFor(3);

        // Проверить успешное создание заказа
        assert(orderPage.isOrderSuccessPopupDisplayed());

        // Проверка заказа через нижнюю кнопку "Заказать"
        driver.navigate().refresh();
        closeCookieBannerIfPresent();
        mainPage.clickOrderButtonBottom();
        pauseFor(3);
        orderPage.fillOrderDetails(name, surname, address, phone);
        pauseFor(3);
        selectMetroStationWithWait(metroStation);
        pauseFor(3);
        orderPage.clickNextButton();
        pauseFor(3);
        orderPage.fillAdditionalOrderDetails(deliveryDate, rentalPeriod, scooterColor, comment);
        pauseFor(3);

        // Проверить успешное создание заказа
        assert(orderPage.isOrderSuccessPopupDisplayed());
    }

    private void selectMetroStationWithWait(String stationName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement metroInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input")));
        metroInput.click();
        pauseFor(1); // Подождем немного, чтобы список был видим

        // Подождем, пока нужная станция появится в списке
        WebElement stationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + stationName + "']")));
        stationOption.click();
    }

    private void pauseFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}*/
