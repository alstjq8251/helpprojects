package com.sparta.helpproject.controller;

import com.sparta.helpproject.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/like/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000,https://week6-six.vercel.app,https://week6-flax.vercel.app", exposedHeaders = "*", allowedHeaders = "*")
@RestController
public class HeartController {
    private final HeartService heartService;

    @Secured("ROLE_USER")
    @PostMapping("/{articleid}")
    public boolean addLikeToMemo(@PathVariable Long articleid) {
        return heartService.addLikeToMemo(articleid);
    }

    @Secured("ROLE_USER")
    @PostMapping("/comment/{commentId}")
    public boolean addLikeToComment(@PathVariable Long commentId) {
        return heartService.addLikeToComment(commentId);
    }
}
