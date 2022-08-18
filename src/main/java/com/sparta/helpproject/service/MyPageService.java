package com.sparta.helpproject.service;


import com.sparta.helpproject.dto.MyPageDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyPageService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;
    private MemberRepository memberRepository;
    public MyPageDto readPage() {
        Optional<Member> member = getUserId();
        List<Article> articleList = articleRepository.findAllByUserId(member.get().getUserId());
        List<Comment> commentList = commentRepository.findAllByUserId(member.get().getUserId());
        List<Heart> likecheckmemoList = heartRepository.findAllByUserIdAndArticle_IdIsNotNull(member.get().getUserId());
        List<Heart> likecheckcommentList = heartRepository.findAllByUserIdAndArticle_IdIsNull(member.get().getUserId());
        List<Comment> likecommentList = new ArrayList<>();
        List<Comment> likechildcommentList = new ArrayList<>();
        for(int i=0; i<likecheckcommentList.size(); i++){
            if(likecheckcommentList.get(i).getComment().getParent() == null)
                likecommentList.add(likecheckcommentList.get(i).getComment()); // 댓글
            else
                likechildcommentList.add(likecheckcommentList.get(i).getComment()); // 대댓글
        }
        MyPageDto myPageDto = new MyPageDto();
        return myPageDto;
    }






    public Optional<Member> getUserId() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findByUserId(userId);
        return member;
    }
}
