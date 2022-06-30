package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.miniproject.domain.Schedule;

import java.util.Map;

public interface ScheduleRepository {

    // 일정 등록
    boolean save(Schedule schedule);

    // 일정 조회
    Map<Integer, Schedule> findAllSchedule();

    // 일정 수정
    boolean modify(Schedule schedule);

    // 일정 삭제
    boolean remove(int scheduleNo);

}
