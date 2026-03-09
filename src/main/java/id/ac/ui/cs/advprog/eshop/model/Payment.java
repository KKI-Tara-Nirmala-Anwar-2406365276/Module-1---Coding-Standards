package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {

    private static final String VOUCHER_CODE = "VOUCHER_CODE";
    private static final String BANK_TRANSFER = "BANK_TRANSFER";
    private static final String REJECTED = "REJECTED";

    @Setter
    String id;

    String method;

    @Setter
    String status;

    Map<String, String> paymentData;
    Order order;

    public Payment(Order order, String method, Map<String, String> paymentData) {
        this.id = UUID.randomUUID().toString();
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        if (method.equals(VOUCHER_CODE)) {
            VoucherCodePayment voucherCodePayment = new VoucherCodePayment(paymentData);
            this.status = voucherCodePayment.getStatus();
        } else if (method.equals(BANK_TRANSFER)) {
            BankTransferPayment bankTransferPayment = new BankTransferPayment(paymentData);
            this.status = bankTransferPayment.getStatus();
        } else {
            this.status = REJECTED;
        }
    }

}