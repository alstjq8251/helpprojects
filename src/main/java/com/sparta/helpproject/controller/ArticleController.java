package com.sparta.helpproject.controller;


import com.sparta.helpproject.dto.ArticleDto;
import com.sparta.helpproject.dto.ArticleResponseDto;
import com.sparta.helpproject.model.Article;
import com.sparta.helpproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/article")
@CrossOrigin(origins = "http://localhost:3000,https://week6-six.vercel.app,https://week6-flax.vercel.app", exposedHeaders = "*", allowedHeaders = "*")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/all")
    public List<ArticleResponseDto> readArticle() {
        return articleService.readArticle();
    }

    @GetMapping("/{id}")
    public ArticleResponseDto readArticleSolo(@PathVariable Long id){
        return articleService.readArticleSolo(id);
    }

//    @Secured("ROLE_USER")
    @PostMapping("/auth")
    public Article createArticle(@RequestBody ArticleDto articleDto){
        return articleService.createArticle(articleDto);
    }

//    @Secured("ROLE_USER")
    @PatchMapping("/auth/{id}")
    public Article fixArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto){
        return articleService.fixArticle(id, articleDto);
    }

//    @Secured("ROLE_USER")
    @DeleteMapping("/auth/{id}")
    public boolean deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }

}
