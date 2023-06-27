package com.sparta.myblog.controller;

import com.sparta.myblog.dto.MyblogRequestDto;
import com.sparta.myblog.dto.MyblogResponseDto;
import com.sparta.myblog.service.MyblogService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyblogController {

    private final JdbcTemplate jdbcTemplate;

    public MyblogController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostMapping("/blog")
    public MyblogResponseDto createMyblog(@RequestBody MyblogRequestDto requestDto){
        MyblogService myblogService = new MyblogService(jdbcTemplate);
        return myblogService.createMyblog(requestDto);
    }

    @GetMapping("/blog")
    public List<MyblogResponseDto> getMyblog(){
        MyblogService myblogService = new MyblogService(jdbcTemplate);
        return myblogService.getMyblog();
    }

    @PutMapping("/blog/{id}")
    public Long updateMyblog(@PathVariable Long id, @RequestBody MyblogRequestDto requestDto){
        MyblogService myblogService = new MyblogService(jdbcTemplate);
        return myblogService.updateMyblog(id, requestDto);
    }

    @DeleteMapping("/blog/{id}")
    public Long deleteMyblog(@PathVariable Long id){
        MyblogService myblogService = new MyblogService(jdbcTemplate);
        return myblogService.deleteMyblog(id);
    }
}
