package com.hqt.happyhostel.dto;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bill {
    int billID;
    int roomID;
    double totalMoney;
    String createdDate;
    String billTitle;
    String expiredPaymentDate;
    String paymentDate;
    int status;
    Payment payment;
}
