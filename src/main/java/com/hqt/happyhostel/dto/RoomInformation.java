package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomInformation {
    String hostelName;
    String address;
    String ward;
    String district;
    String city;
}
