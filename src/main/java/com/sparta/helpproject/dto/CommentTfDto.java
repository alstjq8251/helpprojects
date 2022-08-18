package com.sparta.helpproject.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CommentTfDto {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String content;
    private String userId;
    private String userNic;
    private Long heartcnt;
}
