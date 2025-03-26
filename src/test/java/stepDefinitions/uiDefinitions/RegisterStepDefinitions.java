package stepDefinitions.uiDefinitions;

import uitests.utils.WebDriverFactory;
import com.epam.UI.pages.RegisterPage;
import uitests.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class RegisterStepDefinitions {
    private WebDriver driver = WebDriverFactory.getDriver("chrome");
    private RegisterPage registerPage ;
    public RegisterStepDefinitions() {
        // Initialize RegisterPage using DriverFactory or other utility
        this.registerPage = new RegisterPage(driver);
    }

    @Given("the user is on the registration page")
    public void navigateToRegistrationPage() {
        String registerUrl = ConfigReader.get("register.url");
        driver.get(registerUrl); // Navigates to the registration page URL from properties
    }

    @When("the user enters valid registration details")
    public void enterValidRegistrationDetails() {
        registerPage.enterFirstName(ConfigReader.get("register.firstName"));
        registerPage.enterEmail(ConfigReader.get("register.email"));
        registerPage.enterPassword(ConfigReader.get("register.password"));
        registerPage.enterConfirmPassword(ConfigReader.get("register.confirmPassword"));
    }

    @When("the user enters invalid registration details")
    public void enterInvalidRegistrationDetails() {
        registerPage.enterFirstName(""); // Missing first name
        registerPage.enterEmail("invalidEmailFormat"); // Invalid email format
        registerPage.enterPassword(ConfigReader.get("register.password"));
        registerPage.enterConfirmPassword("mismatchedPassword"); // Passwords don't match
    }

    @And("clicks the register button")
    public void clickRegisterButton() {
        registerPage.clickRegister();
    }

    @Then("the user is redirected to the registration success page")
    public void verifyRegistrationSuccess() {
        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration was not successful!");
    }

}
