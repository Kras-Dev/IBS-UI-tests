package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Класс DataBaseConstants содержит константы, необходимые для подключения к базе данных.
 * Он загружает параметры подключения из файла конфигурации {@code database.properties}.
 */
public class DataBaseConstants {
    /** URL базы данных. */
    public static String DB_URL;
    /** Имя пользователя для подключения к базе данных. */
    public static String USER_NAME;
    /** Пароль для подключения к базе данных. */
    public static String PASS;
    /**
     * Статический блок инициализации, который загружает свойства из файла {@code database.properties}.
     */
    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/database.properties")) {
            properties.load(input);
            DB_URL = properties.getProperty("db.url");
            USER_NAME = properties.getProperty("db.username");
            PASS = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
