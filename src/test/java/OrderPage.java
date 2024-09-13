import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private WebDriver driver;

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private By nextButton = By.xpath(".//button[text()='Далее']");
    private By finishOrderButton = By.xpath(".//button[text()='Заказать']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderDetails(String name, String surname, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickFinishOrderButton() {
        driver.findElement(finishOrderButton).click();
    }
}