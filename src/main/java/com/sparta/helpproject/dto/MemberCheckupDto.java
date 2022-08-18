package com.sparta.helpproject.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCheckupDto {

    private String flag; // 아이디 , 닉네임 관련 구분 짓기 위한 플레그

    private String val; // 별명이 들어갈 자리
}
