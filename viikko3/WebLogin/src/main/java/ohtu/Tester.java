package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Luodaan satunnaisia aakkosia uniikkien usernamejen luomista varten, koska myöhemmässä tehtävänannossakin määriteltiin, että
        // usernamet saavat sisältää vain merkkejä a-z, joten numerot eivät kelpaa.
        Random r = new Random();
        char k1 = (char) (r.nextInt(26) + 'a');
        char k2 = (char) (r.nextInt(26) + 'a');
        char k3 = (char) (r.nextInt(26) + 'a');
        char k4 = (char) (r.nextInt(26) + 'a');
        char k5 = (char) (r.nextInt(26) + 'a');

        driver.get("http://localhost:4567");
        
        // Alkuperäinen skenaario

        /*sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);*/

        // Epäonnistunut kirjautuminen

        /*sleep(2);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrongPass");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);*/

        // Uuden käyttäjätunnuksen luominen

        sleep(2);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement((By.name("username")));
        element.sendKeys("uusi" + k1 + k2 + k3 + k4 + k5);
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana123");
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(2);

        // Uloskirjautuminen käyttäjän luomisen jälkeen

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(2);

        // Ohjelman lopetus

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
