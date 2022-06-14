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
    int hostelID;
    int serviceID;
    String serviceName;
    String validDate;
    int servicePrice;
    String unit;
}
