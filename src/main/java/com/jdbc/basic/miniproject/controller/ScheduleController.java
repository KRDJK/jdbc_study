package com.jdbc.basic.miniproject.controller;

import com.jdbc.basic.miniproject.domain.Schedule;
import com.jdbc.basic.miniproject.repository.ScheduleOracleRepo;
import com.jdbc.basic.miniproject.repository.ScheduleRepository;

import java.util.HashMap;
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


}
