package com.sparta.helpproject.controller;

import com.sparta.helpproject.dto.CommentRequestDto;
import com.sparta.helpproject.dto.CommentResponseDto;
import com.sparta.helpproject.model.Comment;
import com.sparta.helpproject.repository.CommentRepository;
import com.sparta.helpproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000,https://week6-six.vercel.app,https://week6-flax.vercel.app", exposedHeaders = "*", allowedHeaders = "*")
@RestController
public class CommentController {
    private final CommentService commentService;

    private final CommentRepository commentRepository;

    @GetMapping("/all")
    public List<CommentResponseDto> readCommentall(){
        return commentService.readCommentall();
//        return commentRepository.findAllByParent_IdIsNullOrderByModifiedAtDesc();
    }

//    @GetMapping("/{id}")
//    public List<Comment> readCommentall(@PathVariable Long id){
//        return commentRepository.findAllByIdAndParent_IdIsNullOrderByModifiedAtDesc(id);
//    }

    @GetMapping("/baby/all")
    public List<Comment> readbabycommentall(){
        return commentRepository.findAllByParent_IdIsNotNullOrderByCreatedAt();
    }

//    @Secured("ROLE_USER")
    @PostMapping("/auth/{postid}")
    public Comment createComment(@PathVariable Long postid, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(postid, commentRequestDto);
    }

//    @Secured("ROLE_USER")
    @PostMapping("/auth/baby/{commentid}")
    public Comment createbabyComment(@PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createbabyComment(commentid , commentRequestDto);
    }

//    @Secured("ROLE_USER")
    @PatchMapping("/auth/{commentid}")
    public Comment fixComment(@PathVariable Long commentid,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.fixComment(commentid,commentRequestDto);
    }

//    @Secured("ROLE_USER")
    @DeleteMapping("/auth/{commentid}")
    public boolean deleteComment(@PathVariable Long commentid){
        return commentService.deleteComment(commentid);
    }
}
