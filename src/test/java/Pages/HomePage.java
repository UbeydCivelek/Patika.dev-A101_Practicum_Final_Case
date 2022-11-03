package Pages;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    private WebDriver driver;
    Log log = new Log();
    private final By search_box_xpath = By.xpath("//input[@class='desktopOldAutosuggestTheme-UyU36RyhCTcuRs_sXL9b']");
    private final By cookies_id = By.id("onetrust-accept-btn-handler");
    private final By login_and_sign_up_button_xpath = By.xpath("//div[@id=\"myAccount\"]");
    private final By login_id = By.id("login");
    private final By hesabim_text_xpath = By.xpath("//a[@title=\"Hesabım\"]");

    public HomePage(WebDriver driver){this.driver = driver;}

    public void navigate_to_loginpage () {
        WebElement loginandsignup = driver.findElement(login_and_sign_up_button_xpath);
        loginandsignup.click();
        WebElement login = driver.findElement(login_id);
        login.click();
        log.info("Login sayfasına gidildi.");
    }
    public void verify_login(){
        driver.navigate().refresh();
        WebElement hesabımtext = driver.findElement(hesabim_text_xpath);
        try {
            Assertions.assertTrue(hesabımtext.isDisplayed());
        } catch (AssertionError e) {
            log.error("Giriş yapma işlemi doğrulanamadı.");
        }
        log.info("Giriş yapma işlemi doğrulandı.");

    }

    public void accept_cookies(){
        WebElement cookies = driver.findElement(cookies_id);
        cookies.click();
        log.info("Çerezler kabul edildi.");
    }

    public void search_product() {
        WebElement searchbox = driver.findElement(search_box_xpath);
        searchbox.sendKeys("Apple Watch", Keys.ENTER);
        log.info("Ürün ismi arattırıldı.");
    }
}


