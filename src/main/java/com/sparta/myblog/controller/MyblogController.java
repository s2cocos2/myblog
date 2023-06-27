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

    @PutMapping("/blog/{id}")
    public Long updateMyblog(@PathVariable Long id, @RequestBody MyblogRequestDto requestDto){
        //해당 글이 DB에 존재하는지 확인
        if(myblogList.containsKey(id)){
            //해당 글 가져오기
            Myblog myblog = myblogList.get(id);

            //글 수정
            myblog.update(requestDto);
            return myblog.getId();
        } else {
            throw new IllegalArgumentException("선택한 글은 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/blog/{id}")
    public Long deleteMyblog(@PathVariable Long id){
        //해당 글이 DB에 존재하는지 확인
        if(myblogList.containsKey(id)){
            //해당 글 삭제하기
            myblogList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 글은 존재하지 않습니다.");
        }
    }

}
