package com.sparta.helpproject.service;


import com.sparta.helpproject.model.Article;
import com.sparta.helpproject.model.Comment;
import com.sparta.helpproject.model.Heart;
import com.sparta.helpproject.model.Member;
import com.sparta.helpproject.repository.ArticleRepository;
import com.sparta.helpproject.repository.CommentRepository;
import com.sparta.helpproject.repository.HeartRepository;
import com.sparta.helpproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final CommentRepository commentRepository;

    private final HeartRepository heartRepository;

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;
    @Transactional
    public boolean addLikeToMemo(Long articleid) {
        Article article = articleRepository.findById(articleid)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        String userId = getUserId();
        if(heartRepository.findByUserIdAndArticle(userId,article).isPresent()){
            heartRepository.delete(heartRepository.findByUserIdAndArticle(userId,article).get());
            return false;
        }
        else
        {
            Heart heart = Heart.builder()
                    .article(article)
                    .userId(userId)
                    .build();
            article.addLike(heart);
            heartRepository.save(heart);
            return true;
        }
    }

    @Transactional
    public boolean addLikeToComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        String userId = getUserId();
        if(heartRepository.findByUserIdAndComment(userId,comment).isPresent()){
            heartRepository.delete(heartRepository.findByUserIdAndComment(userId,comment).get());
            return false;
        }
        else{
            Heart heart = Heart.builder()
                    .comment(comment)
                    .userId(userId)
                    .build();
            comment.addLike(heart);
            heartRepository.save(heart);
            return true;
        }
    }

    public String getUserId() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findByUserId(userId);
        return member.get().getUserId();
    }
}
