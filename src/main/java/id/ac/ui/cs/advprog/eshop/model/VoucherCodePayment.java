package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class VoucherCodePayment {

    String status;
    Map<String, String> paymentData;

    public VoucherCodePayment(Map<String, String> paymentData) {
        this.paymentData = paymentData;
    }

}