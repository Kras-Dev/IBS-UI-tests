import basetestclass.BaseApiTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Feature;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import models.FoodPojo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@Tag("Api")
@DisplayName("Тесты для добавления продукта в список товаров через Api")
@Feature("Тесты для добавления продукта в список товаров через Api")
public class ApiTest extends BaseApiTest{

    @Test
    @DisplayName("Добавление нового товара в список")
    @Order(1)
    void testAddProduct() throws JsonProcessingException {
        FoodPojo newProduct = new FoodPojo("Тропический фрукт", "FRUIT", true);
        // Сначала проверяем наличие товара
        requestSpecification
                .filter(new CookieFilter())
                .when()
                .get("/api/food")
                .then()
                .assertThat().statusCode(200)
                // Убедимся, что товара нет в списке
                .body("name", Matchers.not(hasItem(newProduct.getName())));
        // Сериализация объекта в JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(newProduct);

        requestSpecification
                .body(jsonBody)
                .filter(new CookieFilter())
                .when()
                .post("/api/food")
                .then()
                .assertThat().statusCode(200);
        // Проверяем наличие нового товара в списке
        requestSpecification
                .filter(new CookieFilter())
                .when()
                .get("/api/food")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", hasItem(newProduct.getName()));
    }

    @Test
    @DisplayName("Получение списка всех товаров")
    @Order(2)
    void testGetProducts() throws JsonProcessingException {
        Response response = requestSpecification
                .filter(new CookieFilter())
                .when()
                .get("/api/food")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
        // Десериализация JSON-ответа в список объектов FoodPojo
        ObjectMapper objectMapper = new ObjectMapper();
        List<FoodPojo> foodList = objectMapper.readValue(response.body().asString(),
                new TypeReference<List<FoodPojo>>(){});
        foodList.forEach(food -> {
            System.out.println("Name: " + food.getName());
            System.out.println("Type: " + food.getType());
            System.out.println("Is Exotic: " + food.isExotic());
            System.out.println("---------");
        });

    }

}
