package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Report {
    int reportID;
    String sendDate;
    String content;
    int status;
    String reply;
    String replyDate;
    String completeDate;
    int replyAccountID;
    int sendAccountID;
    int cateID;
}
