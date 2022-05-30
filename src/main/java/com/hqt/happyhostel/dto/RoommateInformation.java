package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoommateInformation {
    int roomateInfoId;
    int renterAccountId;
    String fullName;
    String email;
    String birthday;
    int sex;
    String phone;
    String address;
    String CCCD;
    String parentName;
    String parentPhone;
}
