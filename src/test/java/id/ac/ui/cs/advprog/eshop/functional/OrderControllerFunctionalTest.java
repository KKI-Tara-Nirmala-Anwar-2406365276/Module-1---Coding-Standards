package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class OrderControllerFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @Autowired
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);

        Product product = new Product();
        product.setProductId("eb5589f1-c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);

        order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                List.of(product),
                1708560000L,
                "Safira Sudrajat"
        );
        orderService.createOrder(order);
    }

    @Test
    void orderCreatePageTitleIsCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/order/create");
        assertEquals("Create Order", driver.getTitle());
    }

    @Test
    void orderHistoryPageTitleIsCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/order/history");
        assertEquals("Order History", driver.getTitle());
    }

    @Test
    void orderHistoryPostShowsOrdersByAuthor(ChromeDriver driver) {
        driver.get(baseUrl + "/order/history");

        WebElement authorInput = driver.findElement(By.name("author"));
        authorInput.sendKeys("Safira Sudrajat");

        driver.findElement(By.tagName("button")).click();

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Safira Sudrajat"));
        assertTrue(pageSource.contains(order.getId()));
    }

    @Test
    void orderPayPageTitleIsCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/order/pay/" + order.getId());
        assertEquals("Pay Order", driver.getTitle());
    }

    @Test
    void orderPayPostShowsPaymentIdPage(ChromeDriver driver) {
        driver.get(baseUrl + "/order/pay/" + order.getId());

        WebElement methodInput = driver.findElement(By.name("method"));
        methodInput.sendKeys("BANK_TRANSFER");

        WebElement bankNameInput = driver.findElement(By.name("bankName"));
        bankNameInput.sendKeys("BCA");

        WebElement referenceCodeInput = driver.findElement(By.name("referenceCode"));
        referenceCodeInput.sendKeys("REF123456");

        driver.findElement(By.tagName("button")).click();

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Payment ID"));
    }
}