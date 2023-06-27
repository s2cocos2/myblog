package com.sparta.myblog.dto;

import lombok.Getter;

@Getter
public class MyblogRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}
