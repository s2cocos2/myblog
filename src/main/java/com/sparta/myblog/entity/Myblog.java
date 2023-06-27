package com.sparta.myblog.entity;

import com.sparta.myblog.dto.MyblogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Myblog {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private String password;

    public Myblog(MyblogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
}