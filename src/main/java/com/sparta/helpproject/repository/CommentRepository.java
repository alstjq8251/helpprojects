package com.sparta.helpproject.repository;

import com.sparta.helpproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByParent_IdIsNotNullOrderByCreatedAtDesc();

    List<Comment> findAllByUserId(String userId);


    List<Comment> findAllByParent_IdIsNullOrderByCreatedAtDesc();

    List<Comment> findAllByParent_IdIsNotNullOrderByCreatedAt();

    List<Comment> findAllByParent_IdIsNullOrderByCreatedAt();
}
