package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.miniproject.domain.Employee;

import java.util.Map;

public interface EmployeeRepository {

    // 사원 정보 저장
    boolean save(Employee employee);

    // 개별 사원 정보 조회
    Employee findOne(int empNo);

    // 전체 사원 정보 조회
    Map<Integer, Employee> findAll();

    // 사원 정보 수정
    boolean modify(Employee employee);

    // 사원 정보 삭제
    boolean remove(int empNo);

    // 현재 매달 지출되는 사원 급여 총액
    long sumSalary();
}
