package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String username;
    private String createDate;
    private String expiredDate;
    private int status;
    private int role;
    private HostelOwnerInfo hostelOwnerInfo;
    private ArrayList<RenterInfo> renterInfo;
}
