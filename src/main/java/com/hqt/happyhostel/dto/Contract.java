package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contract {
    int contract_id;
    int room_id;
    int price;
    String startDate;
    String expiration;
    int deposit;
    int hostelOwnerId;
    int renterId;
    int status;
}
