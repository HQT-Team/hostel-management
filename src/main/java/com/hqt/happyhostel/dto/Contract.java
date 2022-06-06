package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    int contract_id;
    int room_id;
    int price;
    String startDate;
    String expiration;
    int deposit;
}
