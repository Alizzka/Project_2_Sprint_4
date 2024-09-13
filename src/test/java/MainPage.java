import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Локатор для выпадающего списка
    private By questionButton = By.xpath("//div[contains(@class, 'accordion__button')]");

    // Локаторы для кнопок "Заказать"
    private By orderButtonTop = By.className("Button_Button__ra12g");
    private By orderButtonBottom = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void openQuestionSection(int questionIndex) {
        driver.findElements(questionButton).get(questionIndex).click();
    }
}
