import basetestclass.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.FoodPage;
import pages.HomePage;

@Tag("SandBox")
@DisplayName("Тесты для добавления продукта в список товаров")
public class ProductAddTest extends BaseTest {
    /**
     * Тест для добавления продукта с параметризированными значениями.
     * Проверяет функциональность добавления продукта в таблицу с различными данными.
     *
     * @param productName Наименование продукта.
     * @param foodType Тип продукта.
     * @param isExotic Признак экзотичности продукта (true/false).
     */
    @ParameterizedTest
    @CsvSource({
            "Морковь, Овощ, false",
            "Банан, Фрукт, true",
            "Слива, Фрукт, false"
    })
    @DisplayName("Добавление продукта в список товаров с параметрическими данными")
    void testAddProduct(String productName, String foodType, String isExotic){
        HomePage homePage = new HomePage(driver);
        homePage.clickSandBoxMenu()
                .clickProductSubMenu();

        FoodPage foodPage = new FoodPage(driver);
        foodPage.verifyPageTitle("Список товаров")
                .clickAddButton()
                .verifyModalMenuLabel("Добавление товара")
                .fillNameField(productName)
                .selectFoodType(foodType)
                .setExoticCheckBox(Boolean.parseBoolean(isExotic))
                .clickSaveButton()
                .verifyLastRow(productName, foodType, isExotic);

        homePage.clickSandBoxMenu()
                .clickResetSubMenu();

        foodPage.verifyResetData(4);
    }
}
