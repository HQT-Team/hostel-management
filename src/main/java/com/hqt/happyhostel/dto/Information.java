package com.hqt.happyhostel.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Information {
    String fullname;
    String email;
    String birthday;
    int sex;
    String phone;
    String address;
    String cccd;
}
