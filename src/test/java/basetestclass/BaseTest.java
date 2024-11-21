package basetestclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static utils.PropertyConstants.BASE_URL;
import static utils.PropertyConstants.PAGE_LOAD_TIMEOUT;

/**
 * Базовый класс для тестов с использованием Selenium WebDriver.
 * Этот класс содержит методы, которые выполняются перед и после каждого теста для настройки и очистки окружения.
 */
public class BaseTest {
    protected WebDriver driver;

    /**
     * Метод, который выполняется перед каждым тестом.
     * Создает новый экземпляр ChromeDriver, устанавливает таймауты и открывает URL-адрес,
     * определенный в базовых константах.
     */
    @BeforeEach
    void beforeEach() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.navigate().refresh();
    }
    /**
     * Метод, который выполняется после каждого теста.
     * Закрывает браузер и освобождает ресурсы, связанные с WebDriver.
     */
    @AfterEach
    void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }
}
