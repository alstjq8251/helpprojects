package com.sparta.helpproject.dto;

import com.sparta.helpproject.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentDto {

    private Long id;

    private String content;

    private String memberName;

    private Long heartcnt;

//    public CommentDto(Comment comment){
//        this.id = comment.getId();
//        this.content = comment.getContent();
//        this.memberName = comment.getMemberName();
//        this.heartcnt = Long.valueOf(comment.getLikeList().size());
//    }
}
