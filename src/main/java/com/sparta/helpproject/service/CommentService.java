package com.sparta.helpproject.service;


import com.sparta.helpproject.dto.CommentRequestDto;
import com.sparta.helpproject.dto.CommentResponseDto;
import com.sparta.helpproject.model.Article;
import com.sparta.helpproject.model.Comment;
import com.sparta.helpproject.model.Member;
import com.sparta.helpproject.repository.ArticleRepository;
import com.sparta.helpproject.repository.CommentRepository;
import com.sparta.helpproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Comment createComment(Long postid, CommentRequestDto commentRequestDto) {
        Article article = articleRepository.findById(postid)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Optional<Member> member1 = getUserId();
        Member member = memberRepository.findByUserId(member1.get().getUserId())
                .orElseThrow(()-> new IllegalArgumentException("로그아웃 한 사용자입니다."));
        Comment comment = Comment.builder()
                .article(article)
                .articleid(article.getId())
                .content(commentRequestDto.getContent())
                .userId(member.getUserId())
                .userNic(member.getUserNic())
                .build();
        article.addComment(comment);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment createbabyComment(Long commentid, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentid)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        Optional<Member> member1 = getUserId();
        Member member = memberRepository.findByUserId(member1.get().getUserId())
                .orElseThrow(()-> new IllegalArgumentException("로그아웃 한 사용자입니다."));
        Comment babycomment = Comment.builder()
                .parent(comment)
                .content(commentRequestDto.getContent())
                .userId(member.getUserId())
                .userNic(member.getUserNic())
                .build();
        comment.addbabyComment(babycomment);
        return commentRepository.save(babycomment);
    }

    @Transactional
    public Comment fixComment(Long commentid, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentid)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        Optional<Member> member1 = getUserId();
        if(!member1.get().getUserId().equals(comment.getUserId())){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        comment.fixComment(commentRequestDto);
        return comment;
    }

    public boolean deleteComment(Long commentid) {
        Comment comment = commentRepository.findById(commentid)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        Optional<Member> member1 = getUserId();
        if(!member1.get().getUserId().equals(comment.getUserId())){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        commentRepository.delete(comment);
        return true;
    }

    public Optional<Member> getUserId() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
        return member;
    }

    public List<CommentResponseDto> readCommentall() {
        List<Comment> commentList = commentRepository.findAllByParent_IdIsNullOrderByCreatedAt();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .articleid(comment.getArticle().getId())
                    .heartcnt(Long.valueOf(comment.getHeartList().size()))
                    .commentbabycnt(Long.valueOf(comment.getCommentbabylist().size()))
                    .userNic(comment.getUserNic())
                    .userId(comment.getUserId())
                    .content(comment.getContent())
                    .id(comment.getId())
                    .build();
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }
}
