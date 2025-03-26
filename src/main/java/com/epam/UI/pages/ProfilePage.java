package com.epam.UI.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    // Constructor to initialize WebDriver
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements in the profile update page
    private By hamburger=By.xpath("//fa-icon[@icon='bars']");
    private By accountSpan = By.xpath("//span[@jhitranslate='global.menu.account.main']");
    private By settingsButton= By.xpath("//span[@jhitranslate='global.menu.account.settings']");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By profileEmailField = By.id("email");
    private By updateProfileButton = By.xpath("//button[normalize-space()='Save']");
    private By successMessage = By.id("success-message");

    // Methods to interact with the elements
    public void enterProfileName(String name) {

        driver.findElement(firstNameField).clear(); // Clear existing value
        driver.findElement(firstNameField).sendKeys("John");
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys("Doe");
    }

    public void enterProfileEmail(String email) {
        driver.findElement(profileEmailField).clear(); // Clear existing value
        driver.findElement(profileEmailField).sendKeys(email);
    }

    public void clickUpdateProfile() {
        driver.findElement(updateProfileButton).click();
    }

    // Method to verify profile update was successful
    public boolean isProfileUpdateSuccessful() {
        return driver.findElement(successMessage).isDisplayed();
    }

    // Additional utility method to retrieve updated profile data
    public String getUpdatedProfileName() {
        return driver.findElement(firstNameField).getAttribute("value");
    }

    public String getUpdatedProfileEmail() {
        return driver.findElement(profileEmailField).getAttribute("value");
    }
}
