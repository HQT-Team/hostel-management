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

public class MailTemplate {
    private String receiveMail;
    private ArrayList<String> receiveMails;
    private String mailObject;
    private String mailBody;

}
