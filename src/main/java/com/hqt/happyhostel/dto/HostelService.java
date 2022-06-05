package com.hqt.happyhostel.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HostelService {
    private int hostelID;
    private int serviceID;
    private double servicePrice;
    private String validDate;
}
