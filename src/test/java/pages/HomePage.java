package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomePage представляет собой страницу начального меню приложения.
 * Этот класс расширяет BasePage и предоставляет методы для взаимодействия с элементами на домашней странице.
 */
public class HomePage extends BasePage{
    @FindBy(id = "navbarDropdown")
    private WebElement sandBoxMenu;

    @FindBy(linkText = "Товары")
    private WebElement foodItem;

    @FindBy(id = "reset")
    private WebElement resetItem;

    /**
     * Конструктор для инициализации экземпляра HomePage.
     *
     * @param driver WebDriver для управления браузером.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
    /**
     * Метод для клика по меню "Песочница".
     *
     * Ждет, пока элемент станет кликабельным, и затем выполняет клик.
     * Если элемент не найден, тест завершится с ошибкой.
     *
     * @return текущий экземпляр HomePage для цепочки вызовов.
     */
    @Step("Клик по меню \"Песочница\"")
    public HomePage clickSandBoxMenu(){
        waitForElementToBeClickable(sandBoxMenu);
        if (sandBoxMenu == null){
            Assertions.fail("Меню 'Песочница' не было найдено");
            return this;
        }
        sandBoxMenu.click();
        return this;
    }
    /**
     * Метод для клика по подменю "Товары".
     *
     * Ждет, пока элемент станет кликабельным, и затем выполняет клик.
     * Если элемент не найден, тест завершится с ошибкой.
     *
     * @return текущий экземпляр HomePage для цепочки вызовов.
     */
    @Step("Клик по подменю \"Товары\"")
    public HomePage clickProductSubMenu(){
        waitForElementToBeClickable(foodItem);
        if(foodItem == null){
            Assertions.fail("Подменю 'Товары' не было найдено");
            return this;
        }
        foodItem.click();
        return this;
    }
    /**
     * Метод для клика по подменю "Сброс данных".
     *
     * Ждет, пока элемент станет кликабельным, и затем выполняет клик.
     * Если элемент не найден, тест завершится с ошибкой.
     *
     * @return текущий экземпляр HomePage для цепочки вызовов.
     */
    @Step("Клик по подменю \"Сброс данных\"")
    public HomePage clickResetSubMenu(){
        waitForElementToBeClickable(resetItem);
        if(resetItem == null){
            Assertions.fail("Подменю 'Сброс данных' не было найдено");
            return this;
        }
        resetItem.click();
        return this;
    }
}
