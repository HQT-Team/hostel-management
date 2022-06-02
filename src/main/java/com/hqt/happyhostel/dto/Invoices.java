package com.hqt.happyhostel.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    int invoiceID;
    int roomID;
    int totalMoney;
    int consumeMonth;
    int consumeYear;
    String createdDate;
    String expiredPaymentDate;
    int paymentStatus;
}
