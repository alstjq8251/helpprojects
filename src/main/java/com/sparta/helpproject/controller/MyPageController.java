package com.sparta.helpproject.controller;

import com.sparta.helpproject.dto.MyPageDto;
import com.sparta.helpproject.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000,https://week6-six.vercel.app,https://week6-flax.vercel.app", exposedHeaders = "*", allowedHeaders = "*")
@RestController
public class MyPageController {

    private final MyPageService myPageService;

    @Secured("ROLE_USER")
    @GetMapping("/mypages")
    public MyPageDto readPage() {
        return myPageService.readPage();
    }
}
