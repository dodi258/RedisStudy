package com.dodi258.study;

import com.dodi258.study.domain.Point;
import com.dodi258.study.domain.PointRedisRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private PointRedisRepository pointRedisRepository;

    @After
    public void tearDown() throws Exception {
        pointRedisRepository.deleteAll();
    }

    @Test
    public void 기본_등록_조회기능() {
        // given
        String id = "dodi258";
        LocalDateTime refreshTime = LocalDateTime.of(2021, 2, 28, 0,  0);
        Point point = Point.builder()
                .id(id)
                .amount(1000L)
                .refreshTime(refreshTime)
                .build();

        // when
        pointRedisRepository.save(point);

        // then
        Point savedPoint = pointRedisRepository.findById(id).get();
        assertThat(savedPoint.getAmount()).isEqualTo(1000L);
        assertThat(savedPoint.getRefreshTime()).isEqualTo(refreshTime);
    }
}
