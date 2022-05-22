package com.hqt.happyhostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelOwnerAccount {
    private String username;
    private String createDate;
    private int status;
    private HostelOwnerInfo hostelOwnerInfo;
}
