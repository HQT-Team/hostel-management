package com.hqt.happyhostel.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    int billID;
    int roomID;
    int totalMoney;
    String createdDate;
    String expiredPaymentDate;
    int status;
    Payment payment;
}
