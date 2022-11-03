package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.Locale;
public class ProductPage {

    private WebDriver driver;
    Log log = new Log();
    private final By text_DigerSaticilar_xpath = By.xpath("//span[@class=\"otherBuyOptions\"]");
    private final By product_name_id = By.id("product-name");
    private final By product_1_price_xpath = By.xpath("//span[@itemprop=\"price\"]");
    private final By product_2_price_xpath = By.xpath("(//span[@class=\"price-text\"])[1]");
    private final By product_1_seller_name_xpath = By.xpath("//a[@data-bind=\"text: product().currentListing.merchantName, attr: { href: product().currentListing.isHepsiburadaProduct ? 'javascript:;' : product().currentListing.merchantPageUrl, 'data-hbus': userInformation() && userInformation().userId && isEventReady() ? productDetailHbus('GoToSellerClick' ): ''}, css: {hepsiburada: product().currentListing.isHepsiburadaProduct}\"]");
    private final By product_2_seller_name_xpath = By.xpath("(//a[@class=\"merchantStore small\"])[1]");
    private final By first_product_add_to_basket_button_xpath = By.xpath("//span[@class=\"addToCartButton\"]");
    private final By second_product_add_to_basket_button_xpath = By.xpath("(//button[@class=\"add-to-basket button small\"])[1]");
    private final By info_close_click_xpath = By.xpath("//a[contains(@class,\"checkoutui-Modal\")]");
    public static String products_name = null;
    public static String product_1_price = null;
    public static String product_2_price = null;
    public static String product_1_seller_name = null;
    public static String product_2_seller_name = null;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    int i = 1;
    public void is_any_other_seller() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        WebElement digersaticilar = driver.findElement(text_DigerSaticilar_xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", digersaticilar);
        try {
        while (i<100)
            if (digersaticilar.isDisplayed()) {
                log.info("Farklı satıcılar olduğu doğrulandı.");
                break;
            }else{
                log.info("Farklı satıcılar yok. Başka ürün seç.");
                ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
                ProductsPage productsPage1 = new ProductsPage(driver);
                driver.close();
                driver.switchTo().window(tabs1.get(0));
                driver.navigate().refresh();
                productsPage1.click_product(i);
                i = i +1;
                is_any_other_seller();
                if (i >= 100){  //100 ürün kontrol edildikten sonra hala farklı satıcı bulamadıysa break;
                    log.error("Hata! 100 ürün taraandı farklı satıcı bulunamadı.");
                    break;
                }}
                }
        catch (Exception e){
            log.error("Arattığınız bu ürün için sayfadaki bütün ürünler sorgulandı.\n Bu ürünlerde farklı satıcılar yok.\n Arattığınız ürün ismini değiştirin.");
        }
    }

    public void products_information(){
        WebElement productname = driver.findElement(product_name_id);
        products_name = productname.getAttribute("textContent")
                .replaceAll(" ", "").toLowerCase(Locale.ROOT).replaceAll("\n", "");
        log.info(products_name);

        WebElement product1price = driver.findElement(product_1_price_xpath);
        product_1_price = product1price.getText();
        log.info(product_1_price);

        WebElement product1sellername = driver.findElement(product_1_seller_name_xpath);
        product_1_seller_name = product1sellername.getText().toLowerCase(Locale.ROOT);
        log.info(product_1_seller_name);

        WebElement product2price = driver.findElement(product_2_price_xpath);
        product_2_price = product2price.getText();
        log.info(product_2_price);

        WebElement product2sellername = driver.findElement(product_2_seller_name_xpath);
        product_2_seller_name = product2sellername.getText().toLowerCase(Locale.ROOT);
        log.info(product_2_seller_name);
    }
    public void add_products_to_basket() throws InterruptedException {
        is_any_other_seller();  //Ürünün başka satıcısı var mı kontrol edilir yoksa ürünler sayfasından farklı bir üründe başka satıcı bulana kadar denemeye devam eder.
        products_information(); //Ürünlerin bilgileri toplanır.

        WebElement firstproductaddbasket = driver.findElement(first_product_add_to_basket_button_xpath);  //Burdan sonraki kısımda ürünler eklenir
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstproductaddbasket);
        Actions actions = new Actions(driver);
        actions.moveToElement(firstproductaddbasket).click().perform();
        WebElement secondproductaddbasket = driver.findElement(second_product_add_to_basket_button_xpath);
        WebElement infocloseclick = driver.findElement(info_close_click_xpath);
        if (infocloseclick.isDisplayed()){
            infocloseclick.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", secondproductaddbasket);
            secondproductaddbasket.click();
            WebElement navigate_basket = driver.findElement(By.xpath("//button[text()=\"Sepete git\"]"));
            navigate_basket.click();
        }else {
            actions.moveToElement(secondproductaddbasket).click().perform();
            WebElement navigate_basket = driver.findElement(By.id("shoppingCart"));
            navigate_basket.click();
        }
        log.info("Ürünler eklendi. Sepete gidiliyor.");
    }

}


