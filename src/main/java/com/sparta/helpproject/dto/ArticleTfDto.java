package com.sparta.helpproject.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ArticleTfDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userNic;
    private int commentCnt;
    private int heartCnt;
}
