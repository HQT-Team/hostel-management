package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Propose {
    private int id;
    private String content;
    private String sendDate;
    private String reply;
    private String replyDate;
    private int status;
    private Account sendAccount;
    private Account replyAccount;
}
