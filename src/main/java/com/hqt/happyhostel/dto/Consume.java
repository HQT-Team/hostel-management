package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Consume {
    int consumeID;
    int roomID;
    int numberElectric;
    int numberWater;
    String updateDate;
    int status;
}

