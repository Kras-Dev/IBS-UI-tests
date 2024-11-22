package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для хранения констант, загружаемых из файла конфигурации.
 * {@code application.properties}
 */
public class PropertyConstants {
    /** Базовый URL приложения */
    public static String BASE_URL;
    /** Таймаут загрузки страниц в секундах */
    public static int PAGE_LOAD_TIMEOUT;
    /** Таймаут ожидания загрузки элементов в секундах */
    public static int ELEMENT_LOAD_TIMEOUT;
/**
 * Статический блок инициализации, который загружает свойства из файла {@code application.properties}.
 */
    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(input);
            BASE_URL = properties.getProperty("base.url");
            PAGE_LOAD_TIMEOUT = Integer.parseInt(properties.getProperty("page.load.timeout"));
            ELEMENT_LOAD_TIMEOUT = Integer.parseInt(properties.getProperty("element.load.timeout"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
