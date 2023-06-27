package com.sparta.myblog.dto;

import com.sparta.myblog.entity.Myblog;
import lombok.Getter;

@Getter
public class MyblogResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String password;

    public MyblogResponseDto(Myblog myblog) {
        this.id = myblog.getId();
        this.title = myblog.getTitle();
        this.username = myblog.getUsername();
        this.contents = myblog.getContents();
        this.password = myblog.getPassword();
    }
}