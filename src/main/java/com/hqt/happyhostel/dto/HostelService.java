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
    private int hostelServiceId;
    private int hostelID;
    private int serviceID;
    private int servicePrice;
    private String validDate;
    private int status;
}
