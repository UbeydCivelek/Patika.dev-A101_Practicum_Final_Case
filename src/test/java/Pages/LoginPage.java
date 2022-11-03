package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
    private WebDriver driver;
    Log log = new Log();
    private final By email_box_xpath = By.xpath("//input[@type=\"text\"]");
    private final By password_box_xpath = By.xpath("//input[@type=\"password\"]");

    public LoginPage(WebDriver driver) {this.driver = driver;}

    public void login() throws InterruptedException {
        Thread.sleep(5000);
        WebElement emailbox = driver.findElement(email_box_xpath);
        Actions actions = new Actions(driver);
        actions.moveToElement(emailbox).click().perform();
        emailbox.sendKeys("selenium.asd123.test32142@gmail.com" + Keys.ENTER );//email adresini gir ve enter.
        WebElement passwordbox = driver.findElement(password_box_xpath);
        passwordbox.sendKeys("Seleniumtest.123");//şifre gir
        Thread.sleep(2000);
        passwordbox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        log.info("Login işlemi yapıldı.");

    }
}
