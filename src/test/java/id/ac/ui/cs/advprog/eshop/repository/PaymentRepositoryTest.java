package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    Payment payment1;
    Payment payment2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

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

        Map<String, String> voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "BCA");
        bankTransferData.put("referenceCode", "REF123456");

        payment1 = new Payment(order, "VOUCHER_CODE", voucherData);
        payment2 = new Payment(order, "BANK_TRANSFER", bankTransferData);
    }

    @Test
    void testSaveCreate() {
        Payment result = paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payment1.getId());

        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        paymentRepository.save(payment1);

        Payment newPayment = new Payment(payment1.getOrder(),
                payment1.getMethod(), payment1.getPaymentData());
        newPayment.id = payment1.getId();

        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payment1.getId());

        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
    }

    @Test
    void testFindByIdIfIdFound() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Payment findResult = paymentRepository.findById(payment2.getId());

        assertEquals(payment2.getId(), findResult.getId());
        assertEquals(payment2.getMethod(), findResult.getMethod());
        assertEquals(payment2.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Payment findResult = paymentRepository.findById("zczc");

        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
    }
}