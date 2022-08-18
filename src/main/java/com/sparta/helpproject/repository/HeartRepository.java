package com.sparta.helpproject.repository;

import com.sparta.helpproject.model.Article;
import com.sparta.helpproject.model.Comment;
import com.sparta.helpproject.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserIdAndComment(String userId, Comment comment);
    Optional<Heart> findByUserIdAndArticle(String userId, Article article);

    List<Heart> findAllByUserIdAndArticle_IdIsNotNull(String userId);

    List<Heart> findAllByUserIdAndArticle_IdIsNull(String userId);
}
