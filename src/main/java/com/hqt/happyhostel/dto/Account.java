package com.hqt.happyhostel.dto;

import lombok.*;

import java.util.ArrayList;


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
    private AccountInfo accountInfo;
    private ArrayList<RoommateInfo> roommateInfo;
}
