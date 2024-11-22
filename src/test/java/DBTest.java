import basetestclass.BaseDBTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Класс DBTest предназначен для тестирования операций с базой данных,
 * связанных с добавлением, выборкой и удалением записей о продуктах.
 * Этот класс наследует функциональность из класса BaseDBTest.
 */
@Tag("DataBase")
@DisplayName("Тесты на добавление продуктов в базу данных")
public class DBTest extends BaseDBTest {
    /**
     * Параметризованный тест, который проверяет добавление записи о продукте в базу данных.
     * @param productName имя продукта, которое будет добавлено в базу данных
     * @param foodType тип продукта (например, VEGETABLE или FRUIT)
     * @param isExotic указывает, является ли продукт экзотическим (true или false)
     * @throws SQLException если возникает ошибка при выполнении SQL-запросов
     */
    @ParameterizedTest(name = "{index}: productName={0}, foodType={1}, isExotic={2}")
    @CsvSource({
            "Свекла, VEGETABLE, false",
            "Апельсин, FRUIT, true",
            "Слива, FRUIT, false"
    })
    @DisplayName("Добавление продукта в базу данных с параметрическими данными")
    void addDBFoodTest(String productName, String foodType, String isExotic) throws SQLException {

        String addQuery = String.format("INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC)" +
                " VALUES ('%s', '%s', %b)",  productName, foodType, Boolean.parseBoolean(isExotic));
        String selectQuery = String.format("SELECT * FROM FOOD WHERE FOOD_NAME = '%s'", productName);
        String deleteQuery = String.format("DELETE FROM FOOD WHERE FOOD_NAME = '%s'", productName);
        // Выполняем добавление
        statement.executeUpdate(addQuery);
        // Выполняем выборку
        ResultSet resultSet = statement.executeQuery(selectQuery);

        Assertions.assertTrue(resultSet.next(), "Результирующий набор не должен быть пустым.");

        int id = resultSet.getInt("FOOD_ID");
        String name = resultSet.getString("FOOD_NAME");
        String type = resultSet.getString("FOOD_TYPE");
        Boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
        //System.out.printf("%d. %s - %s - %b\n", id, name, type, exotic);
        Assertions.assertAll("Проверка добавленных значений",
                () -> Assertions.assertEquals(productName, name),
                () -> Assertions.assertEquals(foodType, type),
                () -> Assertions. assertEquals(Boolean.parseBoolean(isExotic), exotic)
        );
        // Закрываем ResultSet после использования
        resultSet.close();
        statement.executeUpdate(deleteQuery);

//        while(resultSet.next()){
//            int id = resultSet.getInt("FOOD_ID");
//            String name = resultSet.getString("FOOD_NAME");
//            String type = resultSet.getString("FOOD_TYPE");
//            Boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");
//            // Используем %d для int и %s для строк и %b для boolean значений
//            System.out.printf("%d. %s - %s - %b\n", id, name, type, exotic);
//        }
    }
}
