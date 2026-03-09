package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {

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

        if (method.equals("VOUCHER_CODE")) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
                int digitCount = 0;
                for (char c : voucherCode.toCharArray()) {
                    if (Character.isDigit(c)) {
                        digitCount += 1;
                    }
                }

                if (digitCount == 8) {
                    this.status = "SUCCESS";
                } else {
                    this.status = "REJECTED";
                }
            } else {
                this.status = "REJECTED";
            }
        } else if (method.equals("BANK_TRANSFER")) {
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");

            if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
                this.status = "REJECTED";
            } else {
                this.status = "PENDING";
            }
        }
    }

}