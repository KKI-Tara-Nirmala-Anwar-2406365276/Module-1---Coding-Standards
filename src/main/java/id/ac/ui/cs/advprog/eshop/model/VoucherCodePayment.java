package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class VoucherCodePayment {

    private static final String SUCCESS = "SUCCESS";
    private static final String REJECTED = "REJECTED";

    String status;
    Map<String, String> paymentData;

    public VoucherCodePayment(Map<String, String> paymentData) {
        this.paymentData = paymentData;

        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
            int digitCount = 0;
            for (char c : voucherCode.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitCount += 1;
                }
            }

            if (digitCount == 8) {
                this.status = SUCCESS;
            } else {
                this.status = REJECTED;
            }
        } else {
            this.status = REJECTED;
        }
    }

}