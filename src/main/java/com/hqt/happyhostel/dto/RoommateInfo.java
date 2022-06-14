package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoommateInfo {
    private int roommateID;
    private Information information;
    private String parentName;
    private String parentPhone;
}
