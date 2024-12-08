package basetestclass;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


import static utils.ApiConstants.API_URL;

public class BaseApiTest {
    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    @Step("Подготовка конфигурации для API тестов")
    @BeforeAll
    static void setUp() {
        // Инициализируем спецификацию запроса с базовым URI и настройками заголовков
        requestSpecification = RestAssured.given()
                .baseUri(API_URL)
                .contentType(ContentType.JSON)
                // Указываем, что принимаем любой тип ответа
                .accept("*/*");
        // Инициализируем спецификацию ответа
        responseSpecification = RestAssured.expect();
    }

    @Step("Очистка тестовых данных после выполнения тестов")
    @AfterAll
    static void cleanupTestData() {
        resetTestData();
    }

    private static void resetTestData() {
        requestSpecification
                .when()
                .post("/api/data/reset")
                .then()
                .assertThat().statusCode(200);
    }
}
