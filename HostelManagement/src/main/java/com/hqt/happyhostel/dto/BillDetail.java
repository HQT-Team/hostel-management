package com.hqt.happyhostel.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillDetail {
    int billDetailID;
    int consumeIDStart;
    int consumeIDEnd;
    int accountHostelOwnerID;
    int accountRenterID;
}
