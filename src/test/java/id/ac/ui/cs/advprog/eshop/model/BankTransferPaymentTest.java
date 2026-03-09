package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTransferPaymentTest {

    private Map<String, String> validBankTransferData;
    private Map<String, String> invalidBankTransferData;

    @BeforeEach
    void setUp() {
        validBankTransferData = new HashMap<>();
        validBankTransferData.put("bankName", "BCA");
        validBankTransferData.put("referenceCode", "REF123456");

        invalidBankTransferData = new HashMap<>();
        invalidBankTransferData.put("bankName", "");
        invalidBankTransferData.put("referenceCode", "REF123456");
    }

    @Test
    void testValidBankTransfer() {
        BankTransferPayment bankTransferPayment = new BankTransferPayment(validBankTransferData);
        assertEquals("PENDING", bankTransferPayment.getStatus());
    }

    @Test
    void testInvalidBankTransfer() {
        BankTransferPayment bankTransferPayment = new BankTransferPayment(invalidBankTransferData);
        assertEquals("REJECTED", bankTransferPayment.getStatus());
    }
}