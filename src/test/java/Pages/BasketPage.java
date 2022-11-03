package Pages;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Locale;


public class BasketPage {
    private WebDriver driver;
    Log log = new Log();
    private final By my_basket_text_xpath = By.xpath("(//section[@class=\"basket_itemContent_32WPH\"]//div)[1]");
    private final By number_of_products = By.id("basket-item-count");
    private final By product_1_name_in_basketpage_xpath = By.xpath("(//div[@class=\"product_name_3Lh3t\"]/a)[2]");
    private final By product_2_name_in_basketpage_xpath = By.xpath("(//div[@class=\"product_name_3Lh3t\"]/a)[1]");
    private final By product1_sellername_in_basketpage_xpath = By.xpath("(//span[@class=\"merchantLink_2Ii8s\"])[2]");
    private final By product2_sellername_in_basketpage_xpath = By.xpath("(//span[@class=\"merchantLink_2Ii8s\"])[1]");
    private final By product1_price_in_basketpage_xpath = By.xpath("(//div[@data-test-id=\"price-current-price\"])[2]");
    private final By product2_price_in_basketpage_xpath = By.xpath("(//div[@data-test-id=\"price-current-price\"])[1]");
    private final By delete_button_xpath = By.xpath("//a[@aria-label=\"Ürünü Kaldır\"]");


    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verify_products_information(){
        log.info("Ürün bilgileri doğrulanıyor.");
        verify_automation_is_on_my_basket_page();
        verify_product_names();
        verify_number_of_products();
        verify_seller_names();
        verify_price();
        log.info("Ürün bilgilerinin doğrulama işlemi tamamlandı.");
    }
    public void verify_product_names(){
        WebElement product1nameinbasketpage = driver.findElement(product_1_name_in_basketpage_xpath);//Basketpagede ki isimler tanımlandı ve değişkene atandı.
        WebElement product2nameinbasketpage = driver.findElement(product_2_name_in_basketpage_xpath);//Karşılaştırma için boşluklar silindi ve bütün harfler küçük harf yapıldı.
        String Str_product1nameinbasketpage = product1nameinbasketpage.getText().replace(" ","")
                .toLowerCase(Locale.ROOT);
        String Str_product2nameinbasketpage = product2nameinbasketpage.getText().replace(" ","")
                .toLowerCase(Locale.ROOT);

        Assertions.assertEquals(ProductPage.products_name, Str_product1nameinbasketpage,"Hata! 1. ürünün isimleri farklı.");
        log.info("İlk ürünün ismi doğrulandı.");
        Assertions.assertEquals(ProductPage.products_name, Str_product2nameinbasketpage,"Hata! 2. ürünün isimleri farklı.");
        log.info("İkinci ürünün ismi doğrulandı.");

    }

    public void verify_automation_is_on_my_basket_page(){
        WebElement sepetimtext = driver.findElement(my_basket_text_xpath);
        String str_sepetim = sepetimtext.getText();
        Assertions.assertEquals("Sepetim", str_sepetim, "Hata! 'Sepetim' sayfasına gelindiği doğrulanamadı.");
        log.info("Sepetim sayfasına geldiği doğrulandı.");
    }
    public void verify_number_of_products(){
        WebElement numberofproducts = driver.findElement(number_of_products);
        String str_numberofproducts = numberofproducts.getText();
        Assert.assertEquals("2",str_numberofproducts);
        log.info("Ürün sayısı doğrulandı.");
    }
    public void verify_seller_names(){
        WebElement product1sellernameinbasketpage = driver.findElement(product1_sellername_in_basketpage_xpath);//Basketpagede ki satıcı isimleri tanımlandı ve değişkene atandı.
        WebElement product2sellernameinbasketpage = driver.findElement(product2_sellername_in_basketpage_xpath);//Karşılaştırma için bütün harfler küçük harf yapıldı.
        String str_product1sellernameinbasketpage = product1sellernameinbasketpage.getText().toLowerCase(Locale.ROOT);
        String str_product2sellernameinbasketpage = product2sellernameinbasketpage.getText().toLowerCase(Locale.ROOT);

        Assertions.assertEquals(ProductPage.product_1_seller_name,str_product1sellernameinbasketpage, "Hata! 1. ürünün satıcı ismi yanlış.");
        log.info("İlk ürünün satıcı ismi doğrulandı.");
        Assertions.assertEquals(ProductPage.product_2_seller_name,str_product2sellernameinbasketpage, "Hata! 2. ürünün satıcı ismi yanlış.");
        log.info("İkinci ürünün satıcı ismi doğrulandı.");
    }
    public void verify_price(){
        WebElement product1priceinbasketpage = driver.findElement(product1_price_in_basketpage_xpath);//Basketpagede ki ürün fiyatları tanımlandı ve değikene atandı.
        WebElement product2priceinbasketpage = driver.findElement(product2_price_in_basketpage_xpath);
        String str_product1priceinbasketpage = product1priceinbasketpage.getText();
        String str_product2priceinbasketpage = product2priceinbasketpage.getText();

        Assertions.assertEquals(ProductPage.product_1_price, str_product1priceinbasketpage,"Hata! 1. ürünün fiyatı yanlış.");
        log.info("İlk ürünün fiyatı doğrulandı.");
        Assertions.assertEquals(ProductPage.product_2_price, str_product2priceinbasketpage,"Hata! 2. ürünün fiyatı yanlış.");
        log.info("İkinci ürünün fiyatı doğrulandı.");
    }
    public void delete_product() throws InterruptedException {
        WebElement deletebutton = driver.findElement(delete_button_xpath);
        deletebutton.click();
        driver.navigate().refresh();
        Thread.sleep(2000);
        WebElement deletebutton1 = driver.findElement(delete_button_xpath);
        deletebutton1.click();
        Thread.sleep(2000);
        log.info("Sepetteki ürünler silindi.");
    }

}


