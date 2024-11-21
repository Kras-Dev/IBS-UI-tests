package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Класс FoodPage представляет страницу, связанную с управлением товарами в приложении.
 * Он содержит методы для взаимодействия с элементами страницы и выполнения
 * проверок, связанных с продуктами.
 */
public class FoodPage extends BasePage{
    @FindBy(xpath = "//h5[text() = 'Список товаров']")
    private WebElement foodPageTitle;

    @FindBy(xpath = "//button[text() = 'Добавить']")
    private WebElement addButton;

    @FindBy(id = "editModalLabel")
    private WebElement modalMenuLabel;

    @FindBy(id = "name")
    private WebElement foodNameField;

    @FindBy(id = "type")
    private WebElement foodTypeList;

    @FindBy(id = "exotic")
    private WebElement exoticCheckBox;

    @FindBy(id = "save")
    private WebElement saveButton;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> tableRows;
    /**
     * Конструктор класса FoodPage.
     *
     * @param driver WebDriver для управления браузером
     */
    public FoodPage(WebDriver driver) {
        super(driver);
    }
    /**
     * Проверка заголовка страницы.
     *
     * @param title Ожидаемое название страницы.
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage verifyPageTitle(String title){
        waitForElementToBeVisible(foodPageTitle);
        Assertions.assertEquals(title, foodPageTitle.getText(), "Заголовок страницы не совпадает с ожидаемым");
        return this;
    }
    /**
     * Нажимает на кнопку "Добавить".
     *
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage clickAddButton(){
        waitForElementToBeClickable(addButton);
        if(addButton == null){
            Assertions.fail("Кнопка 'Добавить' не была найдена");
            return this;
        }
        addButton.click();
        return this;
    }
    /**
     * Проверка заголовка модального окна при добавлении продукта.
     *
     * @param title Ожидаемый заголовок модального окна.
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage verifyModalMenuLabel(String title){
        waitForElementToBeVisible(modalMenuLabel);
        Assertions.assertEquals(title, modalMenuLabel.getText(), "Заголовок страницы не совпадает с ожидаемым");
        return this;
    }
    /**
     * Заполнение поля для наименования продукта.
     *
     * @param name Наименование продукта.
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage fillNameField(String name){
        fillInputField(foodNameField, name);
        return this;
    }
    /**
     * Выбор типа продукта из выпадающего списка.
     *
     * @param foodType Тип продукта (например, "овощ", "фрукт").
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage selectFoodType(String foodType){
        Select select = new Select(foodTypeList);
        select.selectByVisibleText(foodType);
        return this;
    }
    /**
     * Установка флага экзотичности для продукта.
     *
     * @param isExotic Флаг экзотичности, true для экзотичного продукта, false - для обычного.
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage setExoticCheckBox(boolean isExotic){
        waitForElementToBeVisible(exoticCheckBox);
        if (isExotic && !exoticCheckBox.isSelected()){
            exoticCheckBox.click();
        }
        else if (!isExotic && exoticCheckBox.isSelected()){
            exoticCheckBox.click();
        }
        return this;
    }
    /**
     * Нажимает на кнопку "Сохранить". для добавления нового продукта.
     *
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage clickSaveButton(){
        waitForElementToBeClickable(saveButton);
        if(saveButton == null){
            Assertions.fail("Кнопка 'Сохранить' не была найдена");
            return this;
        }
        saveButton.click();
        return this;
    }
    /**
     * Проверка последней строки таблицы продуктов после добавления нового продукта.
     *
     * @param expectedName Ожидаемое наименование продукта.
     * @param expectedType Ожидаемый тип продукта.
     * @param expectedExotic Ожидаемое значение флага экзотичности.
     */
    public FoodPage verifyLastRow(String expectedName, String expectedType, String expectedExotic){
        driver.navigate().refresh();
        WebElement lastRow = getLastRow();
        List<WebElement> columns = lastRow.findElements(By.tagName("td"));  // Получаем ячейки в последней строке
        Assertions.assertAll("Проверка добавления товара в список товаров",
                () -> Assertions.assertEquals(expectedName, columns.get(0).getText(), "Наименование не совпадает" +
                        " с ожидаемым"),
                () -> Assertions.assertEquals(expectedType, columns.get(1).getText(), "Тип не совпадает с " +
                        "ожидаемым"),
                () -> Assertions.assertEquals(expectedExotic, columns.get(2).getText(), "Значение 'Экзотичный' " +
                        "не совпадает с ожидаемым")
        );
        return this;
    }
    /**
     * Получение последней строки таблицы.
     *
     * @return Последняя строка таблицы.
     */
    public WebElement getLastRow() {
        List<WebElement> rows = tableRows;
        if (rows.isEmpty()) {
            Assertions.fail("Не найдено ни одной строки в таблице.");
        }
        return rows.get(rows.size() - 1);
    }
    /**
     * Проверка сброса данных на странице
     *
     * @param size Ожидаемое количество строк в таблице после сброса.
     * @return Текущая страница FoodPage для цепочки вызовов.
     */
    public FoodPage verifyResetData(int size){
        driver.navigate().refresh();
        Assertions.assertEquals(size, tableRows.size());
        return this;
    }
}




