package com.sparta.helpproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String content;

    private String userId;

    private String userNic;

    private Long articleid;

    private Long heartcnt;

    private Long commentbabycnt;

}
