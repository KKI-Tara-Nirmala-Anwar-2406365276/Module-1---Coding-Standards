package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_shouldAppearInProductList(ChromeDriver driver) {
        // 1) Open create product page
        driver.get(baseUrl + "/product/create");

        // 2) Fill the form
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));

        String productName = "Functional Test Product";
        String productQty = "123";

        nameInput.clear();
        nameInput.sendKeys(productName);

        quantityInput.clear();
        quantityInput.sendKeys(productQty);

        // 3) Submit the form
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 4) Redirects to list
        // verify product name appears in page source (simple + reliable)
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(productName),
                "Expected product name to appear in product list page.");
    }
}
