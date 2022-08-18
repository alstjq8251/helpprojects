package com.sparta.helpproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.helpproject.Timestamped;
import com.sparta.helpproject.dto.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY : ID값이 서로 영향없이 자기만의 테이블 기준으로 올라간다.
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE) //삭제되어도
    private Comment parent; // 4

    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Comment> commentbabylist = new ArrayList<>();

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userNic;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "Article_Id", nullable = false)
    private Article article;

    @Column
    private Long articleid;

    @JsonManagedReference
    @OneToMany(mappedBy = "comment" , cascade = CascadeType.ALL, orphanRemoval = true)  //부모가 삭제될 때 자식들도 다 삭제되는 어노테이션
    private List<Heart> heartList;

    public void addbabyComment(Comment comment) {
        this.commentbabylist.add(comment);
    }

    public void fixComment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    public void addLike(Heart heart) {
        this.heartList.add(heart);
    }
}
