package com.hqt.happyhostel.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    private int accId;
    private String username;
    private String password;
    private String createDate;
    private int status;
    private int role;
    private int roomId;
    private String otp;
    private String expiredTimeOTP;
    private AccountInfo accountInfo;
    private List<RoommateInfo> roommateInfo;
}
