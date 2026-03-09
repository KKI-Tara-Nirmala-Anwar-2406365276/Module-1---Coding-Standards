package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class BankTransferPayment {

    private static final String PENDING = "PENDING";
    private static final String REJECTED = "REJECTED";

    String status;
    Map<String, String> paymentData;

    public BankTransferPayment(Map<String, String> paymentData) {
        this.paymentData = paymentData;

        if (isValidBankTransfer(paymentData)) {
            this.status = PENDING;
        } else {
            this.status = REJECTED;
        }
    }

    private boolean isValidBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        return bankName != null && !bankName.isEmpty()
                && referenceCode != null && !referenceCode.isEmpty();
    }

}