package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consume {
    int consumeID;
    int roomID;
    int numberElectric;
    int numberWater;
    String startConsumeDate;
    String endConsumeDate;
}

