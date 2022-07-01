package com.jdbc.basic.miniproject.repository;

import com.jdbc.basic.Connect;
import com.jdbc.basic.miniproject.domain.Employee;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EmployeeOracleRepo implements EmployeeRepository {
    @Override
    public boolean save(Employee employee) {
        String sql = "INSERT INTO employee VALUES (seq_employee.nextval, ?, ?, ?, ?)";

        try (Connection conn = Connect.makeConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, employee.getEmpName());
            pstmt.setString(2, employee.getEmpRank());
            pstmt.setString(3, employee.getBirthDay());
            pstmt.setInt(4, employee.getSalary());

            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee findOne(int empNo) {
        String sql = "SELECT * FROM employee WHERE emp_no = ?";

        try (Connection conn = Connect.makeConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, empNo);


            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("emp_no")
                        , rs.getString("emp_nm")
                        , rs.getString("emp_rank")
                        , rs.getString("bir_de")
                        , rs.getInt("salary")
                );
                return employee;
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<Integer, Employee> findAll() {
        Map<Integer, Employee> employeeMap = new HashMap<>();

        String sql = "SELECT * FROM employee ORDER BY emp_no";

        try (Connection conn = Connect.makeConnection()) {
            // 트랜잭션 처리
            conn.setAutoCommit(false); // jdbc는 자동으로 커밋을 해버림. 그걸 방지하기 위해 자동커밋 설정 끈 것.

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { // 한줄 가져오고
                Employee employee = new Employee(
                        rs.getInt("emp_no")
                        , rs.getString("emp_nm")
                        , rs.getString("emp_rank")
                        , rs.getString("bir_de")
                        , rs.getInt("salary")
                );

                employeeMap.put(employee.getEmpNo(), employee);
            }

            return employeeMap;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public boolean modify(Employee employee) {
        String sql = "UPDATE employee SET emp_nm = ?, emp_rank = ?, bir_de = ?, salary = ? WHERE emp_no = ?";

        try (Connection conn = Connect.makeConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, employee.getEmpName());
            pstmt.setString(2, employee.getEmpRank());
            pstmt.setString(3, employee.getBirthDay());
            pstmt.setInt(4, employee.getSalary());
            pstmt.setInt(5, employee.getEmpNo());

            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int empNo) {
        String sql = "DELETE FROM employee WHERE emp_no = ?";

        try (Connection conn = Connect.makeConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, empNo);

            int result = pstmt.executeUpdate();

            if (result != 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long sumSalary() {
        String sql = "SELECT SUM(salary) AS sal_total FROM employee";

        try (Connection conn = Connect.makeConnection()){

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getLong("sal_total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
