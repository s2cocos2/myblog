package com.sparta.myblog.repository;

import com.sparta.myblog.entity.Myblog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MyblogRepository extends JpaRepository<Myblog, Long> {

}
