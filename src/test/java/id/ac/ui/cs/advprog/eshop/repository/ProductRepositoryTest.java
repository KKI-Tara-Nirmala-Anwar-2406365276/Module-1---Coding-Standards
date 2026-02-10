package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb5589f1-c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb5589f1-c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();

        assertTrue(productIterator.hasNext());
        assertEquals(product1.getProductId(), productIterator.next().getProductId());
        assertTrue(productIterator.hasNext());
        assertEquals(product2.getProductId(), productIterator.next().getProductId());
        assertFalse(productIterator.hasNext());
    }
    // Edit tests
    @Test
    void testUpdate_existingProduct_shouldReplaceData() {
        ProductRepository productRepository = new ProductRepository();

        Product original = new Product();
        original.setProductId("p-1");
        original.setProductName("Original");
        original.setProductQuantity(10);
        productRepository.create(original);

        Product updated = new Product();
        updated.setProductId("p-1");
        updated.setProductName("Updated Name");
        updated.setProductQuantity(999);

        Product result = productRepository.update(updated);

        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(999, result.getProductQuantity());

        Product fetched = productRepository.findById("p-1");
        assertNotNull(fetched);
        assertEquals("Updated Name", fetched.getProductName());
        assertEquals(999, fetched.getProductQuantity());
    }

    @Test
    void testUpdate_nonExistingProduct_shouldReturnNull() {
        ProductRepository productRepository = new ProductRepository();

        Product updated = new Product();
        updated.setProductId("does-not-exist");
        updated.setProductName("Nope");
        updated.setProductQuantity(1);

        Product result = productRepository.update(updated);
        assertNull(result);
    }

    // Delete tests

    @Test
    void testDelete_existingProduct_shouldReturnTrueAndRemove() {
        ProductRepository productRepository = new ProductRepository();

        Product product = new Product();
        product.setProductId("p-1");
        product.setProductName("To Delete");
        product.setProductQuantity(5);
        productRepository.create(product);

        boolean deleted = productRepository.delete("p-1");
        assertTrue(deleted);

        assertNull(productRepository.findById("p-1"));
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testDelete_nonExistingProduct_shouldReturnFalse() {
        ProductRepository productRepository = new ProductRepository();

        boolean deleted = productRepository.delete("no-such-id");
        assertFalse(deleted);
    }

}
