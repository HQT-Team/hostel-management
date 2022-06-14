package com.hqt.happyhostel.dto;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HostelService {
    private int hostelID;
    private int serviceID;
    private double servicePrice;
    private String validDate;
}
