package com.sparta.helpproject.controller;


import com.sparta.helpproject.dto.MemberCheckupDto;
import com.sparta.helpproject.dto.MemberRequestDto;
import com.sparta.helpproject.dto.TokenDto;
import com.sparta.helpproject.dto.TokenRequestDto;
import com.sparta.helpproject.model.Member;
import com.sparta.helpproject.repository.MemberRepository;
import com.sparta.helpproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = "http://localhost:3000,https://week6-six.vercel.app,https://week6-flax.verddcel.app", exposedHeaders = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup") // 회원가입
    public boolean signup(@RequestBody MemberRequestDto memberRequestDto) {
        return authService.signup(memberRequestDto);
    }

    @PostMapping("/checkup") // 중복체크
    public boolean checkup(@RequestBody MemberCheckupDto memberCheckupDto) {
        return authService.checkup(memberCheckupDto);
    }
    
    @PostMapping("/login") // 로그인
    public Optional<Member> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(memberRequestDto);
        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.setHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.setHeader("Access-Token-Expire-Time", String.valueOf(tokenDto.getAccessTokenExpiresIn()));
        return memberRepository.findByUserId(memberRequestDto.getUserId());
    }

    @PostMapping("/reissue")  //재발급을 위한 로직
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}