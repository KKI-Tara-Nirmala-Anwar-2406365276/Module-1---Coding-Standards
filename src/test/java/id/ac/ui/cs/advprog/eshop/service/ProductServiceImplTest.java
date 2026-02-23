package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void createProduct_shouldBeRetrievable() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        productService.create(product);

        Product found = productService.findById(product.getProductId());
        assertNotNull(found);
        assertEquals("Test Product", found.getProductName());
        assertEquals(10, found.getProductQuantity());
    }

    @Test
    void deleteProduct_shouldReturnTrue() {
        Product product = new Product();
        product.setProductName("Delete Product");
        product.setProductQuantity(5);

        productService.create(product);

        boolean deleted = productService.delete(product.getProductId());
        assertTrue(deleted);
    }
}