package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomInformation {
    String hostelName;
    String address;
    String ward;
    String district;
    String city;
}
