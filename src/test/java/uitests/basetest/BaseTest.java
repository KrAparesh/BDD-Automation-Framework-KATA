package uitests.basetest;

import org.openqa.selenium.WebDriver;
import uitests.utils.WebDriverFactory;

import java.io.IOException;
import java.util.Properties;
import java.io.FileReader;

public class BaseTest {
    public WebDriver driver;
    public Properties properties;
    public FileReader reader;
    public String browserName;


    public void setUp(String browser) throws IOException {
        driver= WebDriverFactory.getDriver(browser);
        properties= new Properties();
        reader = new FileReader("./src/test/resources/config.properties");
        properties.load(reader);
        browserName = browser;
    }
    public void openPage(String URL) {
        driver.get(URL);

    }
    public void tearDown() {
        driver.quit();
    }
}
