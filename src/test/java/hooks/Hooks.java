package hooks;


import io.cucumber.java.After;
import uitests.utils.WebDriverFactory;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setUp() {
        WebDriverFactory.getDriver("chrome"); // Initialize WebDriver before each scenario
    }

    @After
    public void cleanUp() {
        WebDriverFactory.quitDriver();
    }
}