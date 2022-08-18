package com.sparta.helpproject.dto;


import com.sparta.helpproject.Timestamped;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private String title;
    private String content;
}
