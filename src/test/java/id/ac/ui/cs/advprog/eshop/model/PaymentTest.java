package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Order order;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validBankTransferData;
    private Map<String, String> invalidBankTransferData;

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

        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDCODE12345");

        validBankTransferData = new HashMap<>();
        validBankTransferData.put("bankName", "BCA");
        validBankTransferData.put("referenceCode", "REF123456");

        invalidBankTransferData = new HashMap<>();
        invalidBankTransferData.put("bankName", "");
        invalidBankTransferData.put("referenceCode", "REF123456");
    }

    @Test
    void testCreatePaymentVoucherSuccess() {
        Payment payment = new Payment(order, "VOUCHER_CODE", validVoucherData);

        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testCreatePaymentVoucherRejected() {
        Payment payment = new Payment(order, "VOUCHER_CODE", invalidVoucherData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankTransferSuccess() {
        Payment payment = new Payment(order, "BANK_TRANSFER", validBankTransferData);

        assertEquals(order, payment.getOrder());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals("BCA", payment.getPaymentData().get("bankName"));
        assertEquals("REF123456", payment.getPaymentData().get("referenceCode"));
    }

    @Test
    void testCreatePaymentBankTransferRejected() {
        Payment payment = new Payment(order, "BANK_TRANSFER", invalidBankTransferData);

        assertEquals("REJECTED", payment.getStatus());
    }
}