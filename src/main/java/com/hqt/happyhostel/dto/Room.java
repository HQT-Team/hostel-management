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
public class Room {
    int roomId;
    int hostelId;
    int roomNumber;
    int capacity;
    int roomStatus;
    double roomArea;
    int hasAttic;
    String inviteCode;
    String QRCode;
    Date expiredTimeCode;
    RoomInformation roomInformation;
}
