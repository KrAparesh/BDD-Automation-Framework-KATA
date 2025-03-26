package stepDefinitions.uiDefinitions;

import uitests.utils.WebDriverFactory;
import com.epam.UI.pages.ProfilePage;
import uitests.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.Assert;


public class ProfileStepDefinitions {
    private WebDriver driver = WebDriverFactory.getDriver("chrome");
    private ProfilePage profilePage = new ProfilePage(driver);

    @Given("the user is on the profile page")
    public void navigateToProfilePage() {
        String url = ConfigReader.get("profile.url");
        driver.get(url);
    }

    @When("the user updates the profile details")
    public void updateProfileDetails() {
        profilePage.enterProfileName(ConfigReader.get("profile.name"));
        profilePage.enterProfileEmail(ConfigReader.get("profile.email"));
    }

    @And("clicks the Update Profile button")
    public void clickUpdateProfileButton() {
        profilePage.clickUpdateProfile();
    }

    @Then("the profile update should be successful")
    public void verifyProfileUpdateSuccess() {
        Assert.assertTrue(profilePage.isProfileUpdateSuccessful());
    }
}