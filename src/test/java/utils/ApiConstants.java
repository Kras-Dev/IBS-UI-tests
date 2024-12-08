package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiConstants {
    public static String API_URL;
    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/api.properties")) {
            properties.load(input);
            API_URL = properties.getProperty("base.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
