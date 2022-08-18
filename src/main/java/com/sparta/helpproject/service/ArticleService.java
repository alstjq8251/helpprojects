package com.sparta.helpproject.service;


import com.sparta.helpproject.dto.ArticleDto;
import com.sparta.helpproject.dto.ArticleResponseDto;
import com.sparta.helpproject.model.Article;
import com.sparta.helpproject.model.Member;
import com.sparta.helpproject.repository.ArticleRepository;
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
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public List<ArticleResponseDto> readArticle() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();
        for(Article article : articleList){
            ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .userId(article.getUserId())
                    .userNic(article.getUserNic())
                    .createdAt(article.getCreatedAt())
                    .modifiedAt(article.getModifiedAt())
                    .heartcnt(Long.valueOf(article.getHeartList().size()))
                    .commentcnt(Long.valueOf(article.getCommentList().size()))
                    .build();
            articleResponseDtoList.add(articleResponseDto);
        }
        return articleResponseDtoList;
    }
    public ArticleResponseDto readArticleSolo(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .userNic(article.getUserNic())
                .title(article.getTitle())
                .id(article.getId())
                .content(article.getContent())
                .userNic(article.getUserNic())
                .modifiedAt(article.getModifiedAt())
                .createdAt(article.getCreatedAt())
                .heartcnt(Long.valueOf(article.getHeartList().size()))
                .commentcnt(Long.valueOf(article.getCommentList().size()))
                .build();
        return articleResponseDto;
    }
    @Transactional
    public Article createArticle(ArticleDto articleDto) {
        Optional<Member> member = getUserId();
        Member member1 = memberRepository.findByUserId(member.get().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("로그아웃한 사용자 입니다."));
        Article article = Article.builder()
                .content(articleDto.getContent())
                .title(articleDto.getTitle())
                .userId(member1.getUserId())
                .userNic(member1.getUserNic())
                .build();
        return articleRepository.save(article);
    }
    @Transactional
    public Article fixArticle(Long id, ArticleDto articleDto) {
        Optional<Member> member = getUserId();
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if(!member.get().getUserId().equals(article.getUserId())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        if(articleDto.getContent() == null){
            articleDto.setContent(article.getContent());
        }
        else if(articleDto.getTitle() == null){
            articleDto.setTitle(article.getTitle());
        }
        article.update(articleDto);
        return articleRepository.save(article);
    }

    public boolean deleteArticle(Long id) {
        Optional<Member> member = getUserId();
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if(!member.get().getUserId().equals(article.getUserId())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        articleRepository.delete(article);
        return true;
    }


    public Optional<Member> getUserId() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));
        return member;
    }
}
