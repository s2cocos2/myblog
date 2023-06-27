package com.sparta.myblog.repository;

import com.sparta.myblog.dto.MyblogRequestDto;
import com.sparta.myblog.dto.MyblogResponseDto;
import com.sparta.myblog.entity.Myblog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MyblogRepository {
    private final JdbcTemplate jdbcTemplate;

    public MyblogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Myblog save(Myblog myblog) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); //기본 키를 반환받기

        String sql = "INSERT INTO myblog (username, password, title, contents) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con->{
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, myblog.getUsername());
                    preparedStatement.setString(2, myblog.getPassword());
                    preparedStatement.setString(3, myblog.getTitle());
                    preparedStatement.setString(4, myblog.getContents());
                    return preparedStatement;
                },
                keyHolder);

        //DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        myblog.setId(id);

        return myblog;
    }

    public List<MyblogResponseDto> findAll() {
        //DB 조회
        String sql = "SELECT * FROM blog";

        return jdbcTemplate.query(sql, new RowMapper<MyblogResponseDto>() {
            @Override
            public MyblogResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Myblog 데이터들을 MyblogResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                String password = rs.getString("password");
                String title = rs.getString("title");
                return new MyblogResponseDto(id, username, contents, password, title);
            }
        });
    }

    public void update(Long id, MyblogRequestDto requestDto) {
        String sql = "UPDATE myblog SET username = ?, title = ?, contents = ?, password = ?, WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getPassword(), requestDto.getTitle(), requestDto.getUsername(), requestDto.getContents(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM myblog WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Myblog findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM myblog WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Myblog myblog = new Myblog();
                myblog.setUsername(resultSet.getString("username"));
                myblog.setContents(resultSet.getString("contents"));
                myblog.setPassword(resultSet.getString("password"));
                myblog.setTitle(resultSet.getString("title"));
                return myblog;
            } else {
                return null;
            }
        }, id);
    }
}
