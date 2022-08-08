package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hostel {
    private int hostelID;
    private int hostelOwnerAccountID;
    private String hostelName;
    private String address;
    private String ward;
    private String district;
    private String city;
}
