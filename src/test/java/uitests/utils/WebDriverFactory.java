package uitests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    // ThreadLocal instance of WebDriver to ensure thread safety
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private WebDriverFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Method to get WebDriver based on browser type.
     * Browser is selected using the `-Dbrowser=<browser>` system property.
     */
    public static WebDriver getDriver(String browser) {
        if (threadLocalDriver.get() == null) {
            WebDriver driver = switch (browser) {
                case "chrome" -> createChromeDriver();
                case "firefox" -> createFirefoxDriver();
                case "edge" -> createEdgeDriver();
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            };

            // Set the WebDriver instance to ThreadLocal
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }

    /**
     * Creates a Chrome WebDriver instance.
     */
    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    /**
     * Creates a Firefox WebDriver instance.
     */
    private static WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }

    /**
     * Creates an Edge WebDriver instance.
     */
    private static WebDriver createEdgeDriver() {
        return new EdgeDriver();
    }


    /**
     * Quits the WebDriver instance associated with the current thread.
     */
    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove(); // Clean up ThreadLocal memory
        }
    }
}