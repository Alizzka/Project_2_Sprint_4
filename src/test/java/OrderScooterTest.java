import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Ленина", "89998887766"},
                {"Мария", "Петрова", "ул. Пушкина", "89991234567"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkOrderFlow() {
        // Обработать кнопку cookie, если она отображается
        try {
            WebElement cookieButton = driver.findElement(By.id("rcc-confirm-button"));
            if (cookieButton.isDisplayed()) {
                cookieButton.click();
            }
        } catch (NoSuchElementException e) {
            // Если кнопка cookie не найдена, ничего не делаем
        }

        mainPage.clickOrderButtonTop();
        orderPage.fillOrderDetails(name, surname, address, phone);
        orderPage.clickNextButton();
        orderPage.clickFinishOrderButton();
        // Проверка успешного оформления
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
