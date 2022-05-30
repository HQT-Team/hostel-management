package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    int roomId;
    int hostelId;
    int roomNumber;
    int capacity;
    int roomStatus;
    double roomArea;
    String inviteCode;
    String inviteUrl;
    int hasAttic;
}
