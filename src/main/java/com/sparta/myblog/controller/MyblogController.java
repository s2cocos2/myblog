package com.sparta.myblog.controller;

import com.sparta.myblog.dto.MyblogRequestDto;
import com.sparta.myblog.dto.MyblogResponseDto;
import com.sparta.myblog.entity.Myblog;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyblogController {

    private final Map<Long, Myblog> myblogList = new HashMap<>();
    @PostMapping("/blog")
    public MyblogResponseDto createMyblog(@RequestBody MyblogRequestDto requestDto){
        //RequestDto -> Entity
        Myblog myblog = new Myblog(requestDto);

        //Myblog Max ID Check
        Long maxID = myblogList.size() > 0 ? Collections.max(myblogList.keySet())+1 : 1;
        myblog.setId(maxID);

        //DB 저장
        myblogList.put(myblog.getId(), myblog);

        //Entity -> ResponseDto
        MyblogResponseDto myblogResponseDto = new MyblogResponseDto(myblog);

        return myblogResponseDto;
    }

    @GetMapping("/blog")
    public List<MyblogResponseDto> getMyblog(){
        //Map To List
        List<MyblogResponseDto> responseList = myblogList.values().stream()
                .map(MyblogResponseDto::new).toList();

        return responseList;
    }

}
