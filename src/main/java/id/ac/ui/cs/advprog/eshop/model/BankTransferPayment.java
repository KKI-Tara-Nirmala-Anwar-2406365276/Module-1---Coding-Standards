package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class BankTransferPayment {

    String status;
    Map<String, String> paymentData;

    public BankTransferPayment(Map<String, String> paymentData) {
        this.paymentData = paymentData;
    }

}