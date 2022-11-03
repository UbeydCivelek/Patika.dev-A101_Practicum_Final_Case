package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.*;


public class ProductsPage {
    private WebDriver driver;
    Log log = new Log();
    private final By products_xpath = By.xpath("//li[@id]");
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    public void click_product(int a)  {
        List<WebElement> products = driver.findElements(products_xpath);
        log.info("Sayfadaki ürün sayısı =" + products.size());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", products.get(a));
        Actions action = new Actions(driver);
        action.moveToElement(products.get(a))
                .click()
                .perform();
        log.info(a + ". ürüne tıklandı.");
    }

}

