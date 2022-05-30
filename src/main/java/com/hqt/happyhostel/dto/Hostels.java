package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hostels {
    int hostel_id;
    int hostel_owner_account_id;
    String name;
    String address;
    String ward;
    String district;
    String city;
}
