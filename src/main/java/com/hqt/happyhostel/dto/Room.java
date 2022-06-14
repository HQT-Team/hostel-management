package com.hqt.happyhostel.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
