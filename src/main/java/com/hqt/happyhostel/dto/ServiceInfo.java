package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ServiceInfo {
    int hostelService;
    int hostelID;
    int serviceID;
    String serviceName;
    String validDate;
    double servicePrice;
    String unit;
}
