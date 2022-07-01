package com.jdbc.basic.miniproject.controller;

import com.jdbc.basic.miniproject.domain.Employee;
import com.jdbc.basic.miniproject.repository.EmployeeOracleRepo;
import com.jdbc.basic.miniproject.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {
    private static Map<Integer, Employee> employeeMap;


    private final EmployeeRepository repository;

    static {
        employeeMap = new HashMap<>();
    }


    public EmployeeController() {
        this.repository = new EmployeeOracleRepo();
    }


    // 직원 정보 입력 기능
    public void insertEmployee(Employee employee) {
        employeeMap.put(employee.getEmpNo(), employee);

        repository.save(employee);
    }
    
    
    // 직원 정보 전체 조회
    public List<Employee> findAllEmployee() {
        Map<Integer, Employee> employees = repository.findAll();

        employeeMap = employees;

        List<Employee> employeeList = new ArrayList<>();

        for (Integer empNo : employeeMap.keySet()) {
            employeeList.add(employeeMap.get(empNo));
        }
        return employeeList;
    }


    // 개별 직원 정보 조회
    public Employee findOneEmployee(int empNo) {
        Employee employee = repository.findOne(empNo);

        return employee;
    }


    // 직원 정보 수정
        // 1. 직원 이름 수정
    public boolean updateEmployeeName(int empNo, String empName) {

        if (hasEmployee(empNo)) {
            Employee target = findOneEmployee(empNo);
            target.setEmpName(empName);

            return repository.modify(target);
        } else {
            return false;
        }
    }


    // 2. 직원 직급 수정
    public boolean updateEmployeeRank(int empNo, String empRank) {

        if (hasEmployee(empNo)) {
            Employee target = findOneEmployee(empNo);
            target.setEmpRank(empRank);

            return repository.modify(target);
        } else {
            return false;
        }
    }


    // 3. 직원 생일 정보 수정
    public boolean updateEmployeeBirthDay(int empNo, String birthDay) {

        if (hasEmployee(empNo)) {
            Employee target = findOneEmployee(empNo);
            target.setBirthDay(birthDay);

            return repository.modify(target);
        } else {
            return false;
        }
    }



    // 직원 삭제
    public boolean deleteEmployee(int empNo) {
        return repository.remove(empNo);
    }



    // 사원 번호로 조회했을 때 해당 사원이 존재 유무를 알려주는 메서드
    public boolean hasEmployee(int empNo) {
        return repository.findOne(empNo) != null;
    }


    // 지출될 급여들의 총합을 구해서 알려주는 메서드 (월별 지출 급여 합)
    public long totalSalary() {
        return repository.sumSalary();
    }
}
