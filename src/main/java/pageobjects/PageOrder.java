/*Задание: Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
- Нажать кнопку «Заказать». На странице две кнопки заказа.
- Заполнить форму заказа.
- Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.*/

package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageOrder {
    private final WebDriver driver;

    //Локаторы для первой страницы

    //Локатор для кнопки "Заказать" в верхней части страницы:
    private static final By OrderButtonHeader = By.xpath("/html/body/div/div/div[1]/div[1]/div[2]/button[1]");
    //Локатор для кнопки "Заказать" в нижней части страницы:
    private static final By OrderButtonDown = By.className("Button_UltraBig__UU3Lp");
    //Локатор для поля "Имя":
    private static final By NameButton = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/input");
    //Локатор для поля "Фамилия":
    private static final By SurnameButton = By.xpath("//div[2]/input");
    //Локатор для поля "Адрес: куда привезти заказ":
    private static final By AddressButton = By.xpath("//div[3]/input");
    //Локатор для поля "Станция метро":
    private static final By MetroButton = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div/input");
    //Локатор для поля "Телефон: на него позвонит курьер":
    private static final By TelButton = By.xpath("//div[5]/input");
    //Локатор для кнопки "Далее":
    private static final By NextButton = By.className("Button_Middle__1CSJM");

    //Локаторы для второй страницы

    //Локатор для поля "*Когда привезти самокат":
    private static final By DateButton = By.className("Input_Responsible__1jDKN");
    //Локатор для поля "*Срок аренды":
    private static final By PeriodButton = By.className("Dropdown-placeholder");
    //Локатор для поля "Цвет самоката", с выбором цвета черный жемчуг:
    private static final By BlackButton = By.className("Checkbox_Input__14A2w");
    //Локатор для поля "Цвет самоката", с выбором цвета серая безысходность:
    private static final By GreyButton = By.id("grey");
    //Локатор для поля "Комментарий для курьера":
    private static final By CommentButton = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/input");
    //Локатор для кнопки "Заказать":
    private static final By SubmitButton = By.xpath("/html/body/div[1]/div/div[2]/div[3]/button[2]");
    //Локатор для попапа "Хотите оформить заказ?":
    private static final By PopapButton = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");
    //Локатор для кнопки "Да":
    private static final By YesButton = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");

    public PageOrder(WebDriver driver) {
        this.driver = driver;
    }

    //Заполнение первой страницы заказа:
    public void enterDataFirstPageOrder(String name, String surname, String address, int metro, String phoneNumber) {
        driver.findElement(OrderButtonHeader).click();
        driver.findElement(NameButton).sendKeys(name);
        driver.findElement(SurnameButton).sendKeys(surname);
        driver.findElement(AddressButton).sendKeys(address);
        driver.findElement(MetroButton).click();
        By allMetroStation = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/div/div[2]/ul/li");
        //Список станций метро:
        List<WebElement> elements = driver.findElements(allMetroStation);
        elements.get(metro - 1).click();
        driver.findElement(TelButton).sendKeys(phoneNumber);
        driver.findElement(NextButton).click();
    }

    //Заполнение второй страницы заказа:
    public void enterDataSecondPageOrder(String color, String date, int rentalDays, String comment) {
        if ("черный".equals(color)) {
            driver.findElement(BlackButton).click();
        }
        if ("серый".equals(color)) {
            driver.findElement(GreyButton).click();
        }
        //Выбор даты:
        driver.findElement(DateButton).sendKeys(date);
        driver.findElement(DateButton).sendKeys(Keys.RETURN);
        driver.findElement(PeriodButton).click();
        By allDayForOrder = By.className("Dropdown-option");
        //Список из 7 суток аренды:
        List<WebElement> elements = driver.findElements(allDayForOrder);
        elements.get(rentalDays - 1).click();
        driver.findElement(CommentButton).sendKeys(comment);
        driver.findElement(SubmitButton).click();
        driver.findElement(YesButton).click();
    }

    //Попап подтверждения оформления заказа:
    public boolean successfullyText() {
        String successfullOrder = driver.findElement(PopapButton).getText();
        return successfullOrder.contains("Заказ оформлен");
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
}*/