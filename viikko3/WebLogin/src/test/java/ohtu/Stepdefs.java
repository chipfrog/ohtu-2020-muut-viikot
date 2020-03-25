package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("nonexistent user with username {string} and password {string} tries to login")
    public void nonexistentUserTriesToLogin(String username, String password) {
        logInWith(username, password);
    }

    @Given("command new user is selected")
    public void registerNewUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAreGiven(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("a new user is created")
    public void userIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("too short username {string} and a valid password {string} with matching password confirmation are entered")
    public void tooShortUsernameAndValidPasswordAreGiven(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("user is not created and error \"username should have at least 3 characters\" is reported")
    public void userIsNotCreatedAndErrorAboutTooShortUsernameIsGiven() {
        pageHasContent("username should have at least 3 characters");
    }

    @When("a valid username {string} and too short password {string} with matching password confirmation are entered")
    public void validUsernameAndTooShortPasswordAreGiven(String username, String password) {
        createUserWith(username, password, password);
    }

    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void userIsNotCreatedAndErrorAboutTooShortPasswordIsGiven() {
        pageHasContent("password should have at least 8 characters");
    }

    @When("a valid username {string} and a valid password {string} with non matching password confirmation {string} are entered")
    public void validUsernameAndPasswordAreGivenWithNonMatchingPasswordConfirmation(String username, String password, String passwordConfirmation) {
        createUserWith(username, password, passwordConfirmation);
    }

    @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void userIsNotCreatedAndErrorAboutNonMatchingPasswordsIsGiven() {
        pageHasContent("password and password confirmation do not match");
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userIsSuccessfullyCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        createUserWith(username, password, password);
        logout();
    }

    @When("successfully created user tries to login with username {string} and password {string}")
    public void previouslyCreatedUserEntersLoginInformation(String username, String password) {
        logInWith(username, password);
    }

    @Then("user logs in")
    public void createdUserLogsIn() {
        pageHasContent("Ohtu Application main page");
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userCreationIsTried(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        createUserWith(username, password, password);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
    }

    @When("unsuccessfully created user tries to login with username {string} and password {string}")
    public void userTriesToLoginUnsuccesfully(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error \"invalid username or password\" is reported")
    public void loginFails() {
        pageHasContent("invalid username or password");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */

    private void createUserWith(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void logout() {
        WebElement element = driver.findElement((By.linkText("continue to application mainpage")));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
