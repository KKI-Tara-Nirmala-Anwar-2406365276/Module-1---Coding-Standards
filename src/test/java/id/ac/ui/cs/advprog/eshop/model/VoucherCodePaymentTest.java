package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoucherCodePaymentTest {

    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;

    @BeforeEach
    void setUp() {
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDCODE12345");
    }

    @Test
    void testValidVoucherCode() {
        VoucherCodePayment voucherCodePayment = new VoucherCodePayment(validVoucherData);

        assertEquals("SUCCESS", voucherCodePayment.getStatus());
    }

    @Test
    void testInvalidVoucherCode() {
        VoucherCodePayment voucherCodePayment = new VoucherCodePayment(invalidVoucherData);

        assertEquals("REJECTED", voucherCodePayment.getStatus());
    }
}