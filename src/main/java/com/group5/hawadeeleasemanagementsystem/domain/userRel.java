package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

@Data
public class userRel {
    private Integer userId;
    private Integer colleagueId;
    private Integer leaderId;
    private Integer subordinateId;
}
