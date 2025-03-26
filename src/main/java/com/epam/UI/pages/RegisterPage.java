package com.epam.UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.util.Properties;

public class RegisterPage {
    private WebDriver driver;
    private Properties properties;
    private FileReader reader;

    // Constructor to initialize WebDriver
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators for elements in the registration page
    private final By registerLink=By.xpath("//a[normalize-space()='Register a new account']");
    private final By nameField = By.xpath("//input[@id='login']");
    private By emailField = By.xpath("//input[@id='email']");
    private final By passwordField = By.xpath("//input[@id='password']");
    private final By confirmPasswordField = By.xpath("//input[@id='confirmPassword']");
    private By registerButton = By.xpath("//button[normalize-space()='Register']");

    // Methods to interact with the elements
    public void clickOnRegisterLink(){driver.findElement(registerLink).click();}

    public void enterFirstName(String firstName) {
        driver.findElement(nameField).sendKeys(firstName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    // Method to verify registration was successful
    public boolean isRegistrationSuccessful() {
        return driver.getCurrentUrl().contains("success-page"); // Change "success-page" to your success URL or element to verify
    }
}
