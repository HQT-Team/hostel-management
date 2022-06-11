package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoommateInfo {
    private int roommateID;
    private Information information;
    private String parentName;
    private String parentPhone;
}
