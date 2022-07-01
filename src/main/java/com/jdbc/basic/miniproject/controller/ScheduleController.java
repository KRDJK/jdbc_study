package com.jdbc.basic.miniproject.controller;

import com.jdbc.basic.miniproject.domain.Schedule;
import com.jdbc.basic.miniproject.repository.ScheduleOracleRepo;
import com.jdbc.basic.miniproject.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleController {
    
    private static Map<Integer, Schedule> scheduleMap;
    
    private final ScheduleRepository repository;
    
    static {
        scheduleMap = new HashMap<>();
    }

    public ScheduleController() {
        this.repository = new ScheduleOracleRepo();
    }


    public void insertSchedule(Schedule schedule) {
        scheduleMap.put(schedule.getScheduleNo(), schedule);

        repository.save(schedule);
    }


    public List<Schedule> findAllSchedule() {
        Map<Integer, Schedule> schedule = repository.findAllSchedule();
        scheduleMap = schedule;

        List<Schedule> scheduleList = new ArrayList<>();

        for (Integer scheduleNo : schedule.keySet()) {
            scheduleList.add(scheduleMap.get(scheduleNo));
        }

        return scheduleList;
    }


    public Schedule findOneSchedule(int scheduleNo) {
        Schedule schedule = repository.findOneSchedule(scheduleNo);

        return schedule;
    }


    public boolean deleteSchedule(int scheduleNo) {
        return repository.remove(scheduleNo);
    }


    // 일정 수정
        // 1. 일정 내용 수정
    public boolean updateScheduleName(int scheduleNo, String newScheduleName) {

        Schedule target = repository.findOneSchedule(scheduleNo);

        if (target != null) {
            target.setScheduleName(newScheduleName);
            repository.modify(target);
            return true;
        } else {
            return false;
        }
    }


    // 2. 일정 담당자 수정
    public boolean updateScheduleManager(int scheduleNo, String managerName, String managerRank) {
        Schedule target = repository.findOneSchedule(scheduleNo);

        if (target != null) {
            target.setManagerName(managerName);
            target.setManagerRank(managerRank);
            repository.modify(target);
            return true;
        } else {
            return false;
        }
    }


    // 3. 일정 마감일자 수정
    public boolean updateScheduleEndDate(int scheduleNo, String endDate) {
        Schedule target = repository.findOneSchedule(scheduleNo);

        if (target != null) {
            target.setEndDate(endDate);
            repository.modify(target);
            return true;
        } else {
            return false;
        }
    }

}
