package basetestclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static utils.DataBaseConstants.*;
/**
 * Базовый класс для тестов, связанных с базой данных.
 * Этот класс управляет подключением к базе данных и предоставляет методы
 * для инициализации и очистки ресурсов перед и после каждого теста.
 */
public class BaseDBTest {
    /** Подключение к базе данных. */
    protected Connection connection;
    /** SQL-оператор для выполнения запросов к базе данных. */
    protected Statement statement;
    /**
     * Метод, который выполняется перед каждым тестом.
     * Устанавливает соединение с базой данных и создает объект Statement.
     *
     * @throws SQLException если возникает ошибка при подключении к базе данных
     */
    @BeforeEach
    void beforeEach() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER_NAME, PASS);
        statement = connection.createStatement();
    }
    /**
     * Метод, который выполняется после каждого теста.
     * Закрывает объект Statement и соединение с базой данных
     *
     * @throws SQLException если возникает ошибка при закрытии ресурсов
     */
    @AfterEach
    void afterEach() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
