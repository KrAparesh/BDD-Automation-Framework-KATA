package stepDefinitions.uiDefinitions;

import uitests.utils.WebDriverFactory;
import com.epam.UI.pages.LoginPage;
import uitests.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;


public class LoginStepDefinitions {
    private final WebDriver driver = WebDriverFactory.getDriver("chrome");
    private final LoginPage loginPage = new LoginPage(driver);

    public LoginStepDefinitions () {

    }

    @Given("the user is on the login page")
    public void navigateToLoginPage() {
        String loginUrl = ConfigReader.get("login.url");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(loginUrl);// Navigates to the login page URL from properties
    }

    @When("the user enters valid username and password")
    public void enterValidLoginCredentials() {
        loginPage.enterUsername(ConfigReader.get("login.username"));
        loginPage.enterPassword(ConfigReader.get("login.password"));
    }

    @When("the user enters invalid username and password")
    public void enterInvalidLoginCredentials() {
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPassword");
    }

    @And("clicks the login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user is redirected to the dashboard")
    public void verifyDashboardRedirection() {
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("dashboard"), "User is not on the dashboard!");
    }

}