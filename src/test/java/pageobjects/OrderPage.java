package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By firstMetroSuggestion = By.xpath("//div[contains(@class,'suggestion')]");
    private By nextButton = By.xpath(".//button[text()='Далее']");
    private By whenToDeliverField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.xpath(".//div[@class='Dropdown-placeholder']");
    private By rentalPeriodOption = By.xpath("//div[text()='сутки']");
    private By scooterColorCheckbox = By.xpath("//input[@id='black']");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий к заказу']");
    private By finishOrderButton = By.xpath(".//button[text()='Заказать']");
    private By confirmOrderButton = By.xpath(".//button[text()='Да']");
    private By orderSuccessPopup = By.xpath(".//div[contains(@class,'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Заполнение полей заказа
    public void fillOrderDetails(String name, String surname, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    // Метод для выбора станции метро
    public void selectMetroStation(String metroStation) {
        driver.findElement(metroStationField).sendKeys(metroStation);
        driver.findElement(firstMetroSuggestion).click();
    }

    // Нажатие на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Заполнение полей на следующей странице
    public void fillAdditionalOrderDetails(String deliveryDate, String rentalPeriod, String color, String comment) {
        pauseFor(3);  // Ожидание перед заполнением поля даты
        WebElement whenToDeliverInput = wait.until(ExpectedConditions.elementToBeClickable(whenToDeliverField));
        whenToDeliverInput.click();
        pauseFor(3);  // Ожидание перед выбором даты
        WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--028']")));
        date.click();  // Выбор конкретной даты, можно изменить XPath при необходимости

        pauseFor(3);  // Ожидание перед выбором срока аренды
        WebElement rentalDropdown = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
        rentalDropdown.click();
        WebElement rentalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + rentalPeriod + "']")));
        rentalOption.click();

        pauseFor(3);  // Ожидание перед выбором цвета
        WebElement colorCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='" + color + "']")));
        colorCheckbox.click();

        pauseFor(3);  // Ожидание перед заполнением комментария
        WebElement commentInput = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        commentInput.sendKeys(comment);

        pauseFor(3);  // Ожидание перед завершением заказа
        driver.findElement(finishOrderButton).click();

        // Подтверждение заказа
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        confirmButton.click();
    }

    // Проверка появления всплывающего окна об успешном заказе
    public boolean isOrderSuccessPopupDisplayed() {
        return driver.findElement(orderSuccessPopup).isDisplayed();
    }

    private void pauseFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}









