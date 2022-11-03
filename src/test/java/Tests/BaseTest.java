package Tests;
import Pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {

    WebDriver driver;
    HomePage homePage;
    BasketPage basketPage;
    LoginPage loginPage;
    ProductsPage productsPage;
    ProductPage productPage;
    Log log;


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");
        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        Map<String, Object> prefs = new HashMap<String, Object>();//Google şifre kaydetme popup kapanması için bu ayarları yap
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        log = new Log();
        log.info("Testler başlatıldı.");
        driver.get("https://www.hepsiburada.com/");
        log.info("Hepsiburada sayfasına gidildi.");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        productsPage = new ProductsPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
        log.info("Testler bitti.");
    }


}
