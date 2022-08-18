package com.sparta.helpproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.helpproject.Timestamped;
import com.sparta.helpproject.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


@Getter
@Builder
@Entity
@NoArgsConstructor
@Table(name = "article")
@AllArgsConstructor
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userNic;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "article") //게시글에 달린 댓글 연관관계 매핑
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "article")  //게시글에 달린 좋아요 연관관계 매핑
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Heart> heartList;

    public void update(ArticleDto articleDto){
        this.content = articleDto.getContent();
        this.title = articleDto.getTitle();
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    public void addLike(Heart heart) {
        this.heartList.add(heart);
    }
}
