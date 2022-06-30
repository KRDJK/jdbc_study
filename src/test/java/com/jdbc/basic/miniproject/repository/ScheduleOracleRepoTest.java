package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.miniproject.domain.Schedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleOracleRepoTest {

    ScheduleRepository repository = new ScheduleOracleRepo();

    @Test
    @DisplayName("일정 정보를 DB에 삽입해야 한다.")
    void saveTest() {

        Schedule schedule = new Schedule();

        schedule.setScheduleName("일정 등록 테스트");
        schedule.setEndDate("20220701");
        schedule.setManagerName("홍길동");
        schedule.setManagerRank("부장");

        boolean result = repository.save(schedule);

        assertTrue(result);
    }
}