package com.hqt.happyhostel.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contract {
    private int contractId;
    private int roomId;
    private double price;
    private Date startDate;
    private Date expiration;
    private double deposit;
    private int ownerId;
    private int renterId;
}
