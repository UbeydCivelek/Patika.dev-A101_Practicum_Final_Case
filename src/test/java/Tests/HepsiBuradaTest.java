package Tests;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class HepsiBuradaTest extends BaseTest {

    @Test
    @Order(2)
    public void Hepsiburada_test_without_login() {
        log.info("Case : without login başlatıldı.");
        try {
            homePage.accept_cookies();
            homePage.search_product();
            productsPage.click_product(0);
            productPage.add_products_to_basket();
            basketPage.verify_products_information();
            log.info("Case : without login  başarıyla tamamlandı.\n\n***********************************************************");
        }catch (Exception e){
            log.error("Case : without login başarısız. Hata :\n"+e.getMessage());
        }
    }

    @Test
    @Order(1)
    public void Hepsiburada_test_with_login() {
        log.info("Case : with login başlatıldı.");
        try {
            homePage.accept_cookies();
            homePage.navigate_to_loginpage();
            loginPage.login();
            homePage.verify_login();
            homePage.search_product();
            productsPage.click_product(0);
            productPage.add_products_to_basket();
            basketPage.verify_products_information();
            //basketPage.delete_product();//sonraki testlerde sepette ürün kalmaması için test bittiksen sonra ürünler sepetten silinir.
            log.info("İkinci test(with login) tamamlandı.\n\n***********************************************************");
        }
        catch (Exception e){
            log.error("Case : with login başarısız. Hata :\n"+e.getMessage());
        }
    }
}
