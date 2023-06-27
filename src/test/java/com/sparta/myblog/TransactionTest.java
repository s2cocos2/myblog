package com.sparta.myblog;

import com.sparta.myblog.entity.Myblog;
import com.sparta.myblog.repository.MyblogRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MyblogRepository myblogRepository;

    @Test
    @Transactional
    @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
    @DisplayName("글 생성 성공")
    void test1(){
        Myblog myblog = new Myblog();
        myblog.setUsername("Robbert");
        myblog.setPassword("1234");
        myblog.setTitle("test1제목");
        myblog.setContents("@Transactional 테스트 중!");

        em.persist(myblog);  // 영속성 컨텍스트에 메모 Entity 객체를 저장합니다.
    }

    @Test
    @Disabled
//    @Transactional
//    @Rollback(value = false)
    @DisplayName("트랜잭션 전파 테스트")
    void test2() {
//        myblogRepository.createMyblog(em);
        System.out.println("테스트 test3 메서드 종료");
    }
}