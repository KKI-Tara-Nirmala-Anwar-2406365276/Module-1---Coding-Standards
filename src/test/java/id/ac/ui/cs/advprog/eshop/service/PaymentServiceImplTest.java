package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;
    Payment payment;
    Map<String, String> voucherData;
    Map<String, String> bankTransferData;

    @BeforeEach
    void setUp() {
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

        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "BCA");
        bankTransferData.put("referenceCode", "REF123456");

        payment = new Payment(order, "VOUCHER_CODE", voucherData);
    }

    @Test
    void testAddPayment() {
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, "VOUCHER_CODE", voucherData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(order, result.getOrder());
        assertEquals("VOUCHER_CODE", result.getMethod());
    }

    @Test
    void testSetStatusSuccess() {
        Payment successPayment = new Payment(order, "BANK_TRANSFER", bankTransferData);
        doReturn(successPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(successPayment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());
        verify(paymentRepository, times(1)).save(successPayment);
    }

    @Test
    void testSetStatusRejected() {
        Payment rejectedPayment = new Payment(order, "BANK_TRANSFER", bankTransferData);
        doReturn(rejectedPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(rejectedPayment, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", order.getStatus());
        verify(paymentRepository, times(1)).save(rejectedPayment);
    }

    @Test
    void testGetPaymentIfIdFound() {
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");

        Payment result = paymentService.getPayment("zczc");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = List.of(
                payment,
                new Payment(order, "BANK_TRANSFER", bankTransferData)
        );
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(2, results.size());
    }
}