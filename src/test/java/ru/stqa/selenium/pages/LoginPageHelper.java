package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends  PageBase{
    @FindBy(id = "user")
    WebElement userField;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Log in')]")
    WebElement theSecondLoginButton;
    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(id = "password")
    WebElement passwordFieldNotAtlassian;
    @FindBy(xpath = "//p[@class='error-message']")
    WebElement loginError;


    public LoginPageHelper(WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginButton,30);

    }

    public LoginPageHelper enterAtlLogin(String login) {

        enterValueToTheField(userField,login);
        return this;
    }


    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(loginButton,10);
        loginButton.click();
    }

    public void clickContinueButton() {
        waitUntilElementIsClickable(continueButton,30);
        continueButton.click();
    }
    public void loginToTrelloNotAtlassian(String login, String password){
        this.enterAtlLogin(login);
        enterValueToTheField(passwordFieldNotAtlassian,password);
        loginButton.click();

    }

    public LoginPageHelper loginToTrelloAsAtlassian(String login, String password){
        this.enterAtlLogin(login);
        this.clickLoginWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
        return this;
    }

    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(By.id("password"),30);
        waitUntilElementIsClickable(By.id("login-submit"),30);
        driver.findElement(By.id("password")).sendKeys(password);
        theSecondLoginButton.click();
    }

    public void waitPasswordError() {

        waitUntilElementIsVisible(By
                .xpath("//div[@id = 'login-error']/span"),10);
    }

    public boolean verifyIfPasswordErrorIsCorrect(){
        return driver.findElement(By
                .xpath("//div[@id = 'login-error']/span")).getText()
                .contains("Incorrect email address and / or password.");
    }

    public void waitLoginError() {
        waitUntilElementIsVisible(loginError,20);
    }

    public String getLoginError(){
        return loginError.getText();
    }
}
