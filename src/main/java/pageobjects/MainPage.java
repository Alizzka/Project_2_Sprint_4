//Выпадающий список в разделе «Вопросы о важном». Нужно проверить: когда нажимаешь на стрелочку,
// открывается соответствующий текст.

package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;

public class MainPage {
    private final WebDriver driver;

    //Локаторы вопросов c 1 - 8 из раздела "Вопросы о важном":
    public final static By Question_1 = By.id("accordion__heading-0");
    public final static By Question_2 = By.id("accordion__heading-1");
    public final static By Question_3 = By.id("accordion__heading-2");
    public final static By Question_4 = By.id("accordion__heading-3");
    public final static By Question_5 = By.id("accordion__heading-4");
    public final static By Question_6 = By.id("accordion__heading-5");
    public final static By Question_7 = By.id("accordion__heading-6");
    public final static By Question_8 = By.id("accordion__heading-7");

    //Локаторы ответов с 1 -8 на вопросы из раздела "Вопросы о важном":
    private final static By Answer_1 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[1]/div[2]/p");
    private final static By Answer_2 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[2]/div[2]/p");
    private final static By Answer_3 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[3]/div[2]/p");
    private final static By Answer_4 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[4]/div[2]/p");
    private final static By Answer_5 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[5]/div[2]/p");
    private final static By Answer_6 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[6]/div[2]/p");
    private final static By Answer_7 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[7]/div[2]/p");
    private final static By Answer_8 = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[8]/div[2]/p");


    //Ключ:значение (вопрос:ответ):
    static HashMap<By, By> map = new HashMap<>();
    static {
        map.put(Question_1, Answer_1);
        map.put(Question_2, Answer_2);
        map.put(Question_3, Answer_3);
        map.put(Question_4, Answer_4);
        map.put(Question_5, Answer_5);
        map.put(Question_6, Answer_6);
        map.put(Question_7, Answer_7);
        map.put(Question_8, Answer_8);
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Скролим главную страницу до первого вопроса:
    public void scrollMainPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.id("accordion__heading-0")));
    }

    //Открыть элемент списка вопросов, ждем появления текста с ответом:
    public String getAnswer(By id) {
        driver.findElement(id).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(map.get(id)).getText() != null
                && !driver.findElement(map.get(id)).getText().isEmpty()));
        return driver.findElement(map.get(id)).getText();
    }
}


//говнокод
/*package pageobjects;

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
}*/