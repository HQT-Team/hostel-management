package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    String fullname;
    String email;
    String birthday;
    int sex;
    String phone;
    String address;
    String cccd;
}
