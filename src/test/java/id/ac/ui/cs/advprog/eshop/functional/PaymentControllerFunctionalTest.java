package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class PaymentControllerFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);

        Product product = new Product();
        product.setProductId("eb5589f1-c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);

        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                List.of(product),
                1708560000L,
                "Safira Sudrajat"
        );
        orderService.createOrder(order);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF123456");

        payment = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);
    }

    @Test
    void paymentDetailPageTitleIsCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/payment/detail");
        assertEquals("Payment Detail", driver.getTitle());
    }

    @Test
    void paymentDetailByIdShowsCorrectPage(ChromeDriver driver) {
        driver.get(baseUrl + "/payment/detail/" + payment.getId());
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(payment.getId()));
    }

    @Test
    void paymentAdminListPageTitleIsCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/payment/admin/list");
        assertEquals("Payment Admin List", driver.getTitle());
    }

    @Test
    void paymentAdminDetailPageShowsCorrectPayment(ChromeDriver driver) {
        driver.get(baseUrl + "/payment/admin/detail/" + payment.getId());
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(payment.getId()));
    }

    @Test
    void paymentAdminSetStatusPostWorks(ChromeDriver driver) {
        driver.get(baseUrl + "/payment/admin/detail/" + payment.getId());

        WebElement statusInput = driver.findElement(By.name("status"));
        statusInput.sendKeys("SUCCESS");

        driver.findElement(By.tagName("button")).click();

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("SUCCESS"));
    }
}