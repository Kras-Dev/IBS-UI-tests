package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.PropertyConstants.ELEMENT_LOAD_TIMEOUT;

/**
 * BasePage является базовым классом для страниц тестов, предоставляющим общие методы для работы с
 * элементами веб-страницы.
 */
public class BasePage {
    WebDriver driver;
    /**
     * Конструктор классов страниц. Инициализирует элементы страницы с использованием переданного драйвера.
     *
     * @param driver WebDriver для управления браузером.
     */
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Ожидает, пока указанный элемент станет кликабельным.
     *
     * @param element элемент, который необходимо дождаться.
     * @return кликабельный элемент.
     */
    protected WebElement waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ELEMENT_LOAD_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Ожидает, пока указанный элемент станет видимым на странице.
     *
     * @param element элемент, который необходимо дождаться.
     * @return видимый элемент.
     */
    protected WebElement waitForElementToBeVisible(WebElement element){
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(ELEMENT_LOAD_TIMEOUT));
       return wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Заполняет текстовое поле указанным значением.
     * Прежде чем отправить текст, метод ожидает, пока поле станет кликабельным.
     *
     * @param element элемент текстового поля, которое необходимо заполнить.
     * @param value   строковое значение, которое необходимо ввести.
     */
    public void fillInputField(WebElement element, String value) {
        waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(value);
    }

}
