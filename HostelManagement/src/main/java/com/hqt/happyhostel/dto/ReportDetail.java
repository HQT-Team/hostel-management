package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDetail {
    private Report report;
    private ReportCategory category;
    private Information renterInformation;
    private Room room;
    private Hostel hostel;
}
