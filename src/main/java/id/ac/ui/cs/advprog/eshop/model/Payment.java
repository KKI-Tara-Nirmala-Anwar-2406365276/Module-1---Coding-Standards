package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {

    private static final String VOUCHER_CODE = "VOUCHER_CODE";
    private static final String BANK_TRANSFER = "BANK_TRANSFER";
    private static final String SUCCESS = "SUCCESS";
    private static final String REJECTED = "REJECTED";
    private static final String PENDING = "PENDING";

    @Setter
    String id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(Order order, String method, Map<String, String> paymentData) {
        this.id = UUID.randomUUID().toString();
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        if (method.equals(VOUCHER_CODE)) {
            this.status = isValidVoucher(paymentData.get("voucherCode")) ? SUCCESS : REJECTED;
        } else if (method.equals(BANK_TRANSFER)) {
            this.status = isValidBankTransfer(paymentData) ? PENDING : REJECTED;
        }
    }

    private boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int digitCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount += 1;
            }
        }

        return digitCount == 8;
    }

    private boolean isValidBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        return bankName != null && !bankName.isEmpty()
                && referenceCode != null && !referenceCode.isEmpty();
    }

}