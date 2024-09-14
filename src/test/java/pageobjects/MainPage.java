package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Заголовок страницы
    private By pageTitle = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[4]");

    // Кнопка "Заказать" вверху страницы
    private By orderButtonTop = By.xpath(".//button[text()='Заказать'][1]");

    // Кнопка "Заказать" внизу страницы
    private By orderButtonBottom = By.xpath(".//button[text()='Заказать'][2]");

    // Вопросы и ответы в разделе "Вопросы о важном"
    private By faqSection = By.xpath("//*[@id=\"root\"]/div/div/div[5]/div[1]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 секунд ожидания
    }

    // Нажать на кнопку "Заказать" вверху страницы
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    // Нажать на кнопку "Заказать" внизу страницы
    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    // Открыть выпадающий список FAQ
    public void openFaqQuestion(int index) {
        // Ожидаем, что раздел FAQ будет доступен
        WebElement faqSectionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(faqSection));

        // Находим все вопросы FAQ
        List<WebElement> questions = faqSectionElement.findElements(By.xpath(".//button")); // Предполагается, что вопросы FAQ - это кнопки

        if (index < questions.size()) {
            questions.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("FAQ question index out of bounds.");
        }
    }
}
