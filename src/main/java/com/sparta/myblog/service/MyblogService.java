package com.sparta.myblog.service;

import com.sparta.myblog.dto.MyblogRequestDto;
import com.sparta.myblog.dto.MyblogResponseDto;
import com.sparta.myblog.entity.Myblog;
import com.sparta.myblog.repository.MyblogRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MyblogService {
    private final JdbcTemplate jdbcTemplate;

    public MyblogService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public MyblogResponseDto createMyblog(MyblogRequestDto requestDto) {
        //RequestDto -> Entity
        Myblog myblog = new Myblog(requestDto);

        //DB 저장
        MyblogRepository myblogRepository = new MyblogRepository(jdbcTemplate);
        Myblog saveMyblog = myblogRepository.save(myblog);

        //Entity -> ResponseDto
        MyblogResponseDto myblogResponseDto = new MyblogResponseDto(myblog);

        return myblogResponseDto;
    }

    public List<MyblogResponseDto> getMyblog() {
        //DB 조회
        MyblogRepository myblogRepository = new MyblogRepository(jdbcTemplate);
        return myblogRepository.findAll();
    }

    public Long updateMyblog(Long id, MyblogRequestDto requestDto) {
        MyblogRepository myblogRepository = new MyblogRepository(jdbcTemplate);
        //해당 글이 DB에 존재하는지 확인
        Myblog myblog = myblogRepository.findById(id);
        if(myblog != null){
            //글 내용 수정
            myblogRepository.update(id, requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 글은 존재하지 않습니다.");
        }
    }

    public Long deleteMyblog(Long id) {
        MyblogRepository myblogRepository = new MyblogRepository(jdbcTemplate);
        //해당 글이 DB에 존재하는지 확인
        Myblog myblog = myblogRepository.findById(id);
        if(myblog != null){
            //해당 글 삭제하기
            myblogRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 글은 존재하지 않습니다.");
        }
    }
}
