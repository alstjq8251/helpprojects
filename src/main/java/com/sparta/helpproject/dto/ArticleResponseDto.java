package com.sparta.helpproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.helpproject.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDto {

    private Long id;

    private String userId;

    private String userNic;

    private String title;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년MM월dd일'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt; //LocalDateTime 시간을 나타내는 자료형

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy년MM월dd일'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    private Long heartcnt;

    private Long commentcnt;

    private List<Comment> commentList;
}
